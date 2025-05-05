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
 * 29/4 Samuel Leung & Tao Gao - Applied queries and algorithm for fetching
 * round trips in schedules endpoint
 * 30/4 Samuel Leung - Update castle detail endpoint
 * 2/5 Samuel Leung - Update algorithm to check for castle opening times;
 * add error and no routes found page
 * 4/5 Samuel Leung - Add Itinerary endpoint
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
import org.springframework.util.RouteMatcher;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final CastleInfoRepository castleInfoRepository;
    private final ScheduleInfoRepository scheduleInfoRepository;
    private final RouteStopRepository routeStopRepository;

    @Autowired
    public HomeController(CastleInfoRepository castleInfoRepository, ScheduleInfoRepository scheduleInfoRepository, RouteStopRepository routeStopRepository) {
        this.castleInfoRepository = castleInfoRepository;
        this.scheduleInfoRepository = scheduleInfoRepository;
        this.routeStopRepository = routeStopRepository;
    }

    @GetMapping("/")
    public String homePage() {
        return "HomePage";
    }

    @GetMapping("/castle")
    public String getCastleDetail(@RequestParam("name") String name, Model model) {
        CastleInfo castleInfo = castleInfoRepository.findByName(name);
        String castleName = castleInfo.getName();
        model.addAttribute("castleInfo", castleInfo);
        model.addAttribute("castleName", castleName.substring(0,1).toUpperCase() + castleName.substring(1));
        return "castle.html";
    }

    @GetMapping("/schedules")
    public String selectSchedule(@RequestParam("departTime") LocalTime departTime,
                                 @RequestParam("returnTime") LocalTime returnTime,
                                 @RequestParam("castleName") String castleName,
                                 @RequestParam("noOfVisitors") int noOfVisitors,
                                 Model model) {
        CastleInfo castleInfo = castleInfoRepository.findByName(castleName);

        if (castleInfo == null) {
            model.addAttribute("message", "No such castles found.");
            return "error";
        }
        
        String castleId = castleInfo.getCastleId();
        double entryFee = castleInfo.getEntryFee();
        LocalTime closeTime = castleInfo.getCloseTime();

        List<ScheduleInfo> outboundSchedules = scheduleInfoRepository.findOutboundSchedules(departTime, departTime.plusHours(1), castleId);

        List<RoundTripDTO> roundTripList = new ArrayList<>();
        List<RouteStop> outboundTransfers, returnTransfers;

        for(ScheduleInfo outbound : outboundSchedules){
            List<ScheduleInfo> returnSchedules = scheduleInfoRepository.findReturnSchedules(returnTime, returnTime.plusHours(1), castleId);
            for(ScheduleInfo returnOption : returnSchedules){
                LocalTime castleFinishTime = outbound.getArriveTime().plusHours(2);
                if(castleFinishTime.isBefore(returnOption.getDepartTime()) && castleFinishTime.isBefore(closeTime)){
                    List<RouteStop> outboundStops = routeStopRepository.findRouteStopsByRouteId(outbound.getRouteId());
                    List<RouteStop> returnStops = routeStopRepository.findRouteStopsByRouteId(returnOption.getRouteId());
                    if(outboundStops.isEmpty() || returnStops.isEmpty()){
                        model.addAttribute("message", "Stops missing.");
                        return "error";
                    }
//                    if (outboundStops.size() > 2)
//                        outboundTransfers = outboundStops.subList(1, outboundStops.size());
//                    if (returnStops.size() > 2)
//                        returnTransfers = outboundStops.subList(1, returnStops.size());
                    RoundTripDTO dto = new RoundTripDTO(outbound, returnOption, outboundStops, returnStops);
                    dto.setTotalPrice(entryFee, noOfVisitors);
                    roundTripList.add(dto);
                }
            }
        }

        if(roundTripList.isEmpty()){
            model.addAttribute("message", "No itineraries found.");
            return "error";
        }
        else{
            model.addAttribute("roundTripList", roundTripList);
            model.addAttribute("castleName", castleName.substring(0, 1).toUpperCase() + castleName.substring(1));
            return "routeSelection";
        }
    }

    @GetMapping("/itinerary")
    public String displayItinerary(@RequestParam("outboundId") String outboundId, @RequestParam("returnId") String returnId, Model model) {
        System.out.println(outboundId);
        model.addAttribute("message", "ID is " + outboundId + " and " + returnId);
        return "error";
    }
}
