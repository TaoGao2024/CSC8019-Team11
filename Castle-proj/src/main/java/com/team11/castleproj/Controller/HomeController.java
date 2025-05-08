/*
 * Creation Date: Mar 26, 2025
 * Class for controller to handle incoming requests
 * and rendering templates including the home page,
 * round trip or route selection, and itinerary
 * output.
 *
 * @author Samuel Leung
 * @version 3.1
 *
 * Modification history:
 * 5/4 Samuel Leung - Added sample queries to castle
 * 27/4 Tao Gao - Added routes and castle detail endpoint
 * 29/4 Tao Gao - Applied queries and algorithm for fetching
 * round trips in schedules endpoint
 * 30/4 Samuel Leung - Update castle detail endpoint
 * 2/5 Samuel Leung - Update algorithm to check for castle opening times;
 * add error and no routes found page
 * 4/5 Samuel Leung - Add Itinerary endpoint
 * 5/5 Samuel Leung - Simplify logic of controllers
 * 7/5 Samuel Leung - Update logic to account for day availability of routes
 * 8/5 Samuel Leung - Add JavaDocs
 */
package com.team11.castleproj.Controller;

import com.team11.castleproj.DTO.RoundTripDTO;
import com.team11.castleproj.Entity.CastleInfo;
import com.team11.castleproj.Entity.RouteStop;
import com.team11.castleproj.Entity.ScheduleInfo;
import com.team11.castleproj.Repository.CastleInfoRepository;
import com.team11.castleproj.Repository.RouteStopRepository;
import com.team11.castleproj.Repository.ScheduleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {
    private final CastleInfoRepository castleRepo;
    private final ScheduleInfoRepository scheduleRepo;
    private final RouteStopRepository routeStopRepo;
    private final List<String> weekdays;

    @Autowired
    public HomeController(CastleInfoRepository castleRepo,
                          ScheduleInfoRepository scheduleRepo,
                          RouteStopRepository routeStopRepo) {
        this.castleRepo = castleRepo;
        this.scheduleRepo = scheduleRepo;
        this.routeStopRepo = routeStopRepo;
        this.weekdays = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
    }

    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }

    /**
     * Fetches the castle information object given a castle name and displays
     * it to the user.
     * @param name castle name
     * @param model model object to pass to view
     * @return castle html, otherwise error html with specific message
     */
    @GetMapping("/castle")
    public String getCastleDetail(@RequestParam("name") String name, Model model) {
        CastleInfo castleInfo = castleRepo.findByName(name.toLowerCase());
        model.addAttribute("castleInfo", castleInfo);
        model.addAttribute("castleName", name);
        if (castleInfo == null) {
            model.addAttribute("message", "No such castles found.");
            return "error";
        }
        return "castle.html";
    }

    /**
     * Given the following form parameters filled by the users,
     * queries for schedules, filtering and returning
     * relevant round trips
     * @param departTime depart time object (e.g. 8:00)
     * @param returnTime return time object (e.g. 16:00)
     * @param castleName name of castle visited
     * @param noOfVisitors number of visitors
     * @param travelDay day of week for castle visit
     * @param model
     * @return schedule html, otherwise error html with specific message
     */
    @GetMapping("/schedules")
    public String selectSchedule(@RequestParam("departTime") LocalTime departTime,
                                 @RequestParam("returnTime") LocalTime returnTime,
                                 @RequestParam("castleName") String castleName,
                                 @RequestParam("noOfVisitors") int noOfVisitors,
                                 @RequestParam("travelDay") String travelDay,
                                 Model model) {
        CastleInfo castleInfo = castleRepo.findByName(castleName.toLowerCase());
        if (castleInfo == null) {
            model.addAttribute("message", "No such castles found.");
            return "error";
        }

        String castleId = castleInfo.getCastleId();
        double entryFee = castleInfo.getEntryFee();
        LocalTime openTime = castleInfo.getOpenTime();
        LocalTime closeTime = castleInfo.getCloseTime();
        String availability = weekdays.contains(travelDay) ? "Weekday" : travelDay;

        List<ScheduleInfo> outboundSchedules = scheduleRepo.findOutboundSchedules(departTime,
                departTime.plusHours(1),
                castleId,
                availability);
        List<ScheduleInfo> returnSchedules = scheduleRepo.findReturnSchedules(returnTime,
                returnTime.plusHours(2),
                castleId,
                availability);
        List<RoundTripDTO> roundTripList = new ArrayList<>();
        // earliest time after castle opening that user returns
        LocalTime earliestReturn = openTime.plusHours(2);

        for (ScheduleInfo outbound : outboundSchedules) {
            for (ScheduleInfo returnOption : returnSchedules) {
                // recommended earliest time post-arrival that castle trip ends
                LocalTime earliestFinish = outbound.getArriveTime().plusHours(2);
                if (earliestFinish.isBefore(returnOption.getDepartTime())
                        && earliestFinish.isBefore(closeTime)
                        && earliestReturn.isBefore(returnOption.getDepartTime())) {
                    RoundTripDTO dto = new RoundTripDTO(outbound, returnOption);
                    dto.calcTotalPrice(entryFee, noOfVisitors);
                    roundTripList.add(dto);
                }
            }
        }
        roundTripList = roundTripList.size() > 6 ? roundTripList.subList(0, 6) : roundTripList;

        if (roundTripList.isEmpty()) {
            model.addAttribute("message", "No itineraries found. Please try adjusting the time or day of your visit.");
            return "error";
        } else {
            model.addAttribute("roundTripList", roundTripList);
            model.addAttribute("castleName", castleName);
            model.addAttribute("noOfVisitors", noOfVisitors);
            return "routeSelection";
        }
    }

    /**
     * Fetches the relevant itinerary data including the castle information,
     * schedules, stops, and transfers, displaying it back to the user
     * @param outboundId ID of outbound schedule
     * @param returnId ID of return schedule
     * @param castleName name of castle
     * @param noOfVisitors number of visitors
     * @param totalPrice total price given visitors, castle entry, and bus
     * @param model model object to pass to view
     * @return itinerary html, otherwise error html with specific message
     */
    @GetMapping("/itinerary")
    public String displayItinerary(@RequestParam("outboundId") String outboundId,
                                   @RequestParam("returnId") String returnId,
                                   @RequestParam("castleName") String castleName,
                                   @RequestParam("noOfVisitors") int noOfVisitors,
                                   @RequestParam("totalPrice") double totalPrice,
                                   Model model) {
        ScheduleInfo outbound = scheduleRepo.findByScheduleId(outboundId);
        ScheduleInfo returnOption = scheduleRepo.findByScheduleId(returnId);
        List<RouteStop> outboundStops = routeStopRepo.findRouteStopsByRouteId(outbound.getRouteId());
        List<RouteStop> returnStops = routeStopRepo.findRouteStopsByRouteId(returnOption.getRouteId());
        if (outboundStops.isEmpty() || returnStops.isEmpty()) {
            model.addAttribute("message", "Error: Bus stops missing.");
            return "error";
        }

        RoundTripDTO dto = new RoundTripDTO(outbound, returnOption, outboundStops, returnStops);
        dto.setTotalPrice(totalPrice);
        CastleInfo castleInfo = castleRepo.findByName(castleName.toLowerCase());

        model.addAttribute("trip", dto);
        model.addAttribute("castleInfo", castleInfo);
        model.addAttribute("castleName", castleName);
        model.addAttribute("noOfVisitors", noOfVisitors);
        return "itinerary";
    }
}
