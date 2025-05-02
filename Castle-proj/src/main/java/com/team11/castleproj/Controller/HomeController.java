package com.team11.castleproj.Controller;

import com.team11.castleproj.DTO.RoundTripDTO;
import com.team11.castleproj.Entity.CastleInfo;
import com.team11.castleproj.Entity.RouteInfo;
import com.team11.castleproj.Entity.ScheduleInfo;
import com.team11.castleproj.Repository.CastleInfoRepository;
import com.team11.castleproj.Repository.RouteInfoRepository;
import com.team11.castleproj.Repository.ScheduleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final CastleInfoRepository castleInfoRepository;
    private final ScheduleInfoRepository scheduleInfoRepository;

    @Autowired
    public HomeController(CastleInfoRepository castleInfoRepository, RouteInfoRepository routeInfoRepository,ScheduleInfoRepository scheduleInfoRepository) {
        this.castleInfoRepository = castleInfoRepository;
        this.scheduleInfoRepository = scheduleInfoRepository;
    }

    @GetMapping("/")
    public String homePage() {
        return "HomePage";
    }

    @GetMapping("/castle")
    public String getCastleDetail(@RequestParam("name") String name, Model model) {
        List<CastleInfo> result = castleInfoRepository.findByName(name);
        System.out.println(result);
        String castleName = result.get(0).getName();
        model.addAttribute("castleInfo", result.get(0));
        model.addAttribute("castleName", castleName.substring(0,1).toUpperCase() + castleName.substring(1));
        return "castle.html";
    }

//    @GetMapping("/routes")
//    public String getRoutes(@RequestParam("castleId")String castleId,@RequestParam("direction")boolean direction,Model model){
//        if(direction){
//            List<RouteInfo> results = routeInfoRepository.findDepartureRoutesByCastle(castleId);
//            if (results.isEmpty()) {
//                model.addAttribute("error", "No routes found");
//            } else {
//                model.addAttribute("routeList", results);
//            }
//        }else{
//            List<RouteInfo> results = routeInfoRepository.findReturnRoutesByCastle(castleId);
//            if (results.isEmpty()) {
//                model.addAttribute("error", "No routes found");
//            }else {
//                model.addAttribute("routeList", results);
//            }
//        }
//        return "routes.html";
//    }


    @GetMapping("/itinerary")
    public String selectSchedule(@RequestParam("departTime")LocalTime departTime,
                                 @RequestParam("returnTime")LocalTime returnTime,
                                 @RequestParam("castleName")String castleName,
                                 @RequestParam("noOfVisitors")int noOfVisitors,
                                 Model model) {
        List<CastleInfo> castleInfoList = castleInfoRepository.findByName(castleName);

        if (castleInfoList.isEmpty()){
            model.addAttribute("message", "No such castles found.");
            return "error";
        }
        
        String castleId = castleInfoList.get(0).getCastleId();
        double entryFee = castleInfoList.get(0).getEntryFee();
        LocalTime closeTime = castleInfoList.get(0).getCloseTime();

        List<ScheduleInfo> outboundSchedules = scheduleInfoRepository.findOutboundSchedules(departTime,departTime.plusHours(1),castleId);

        List<RoundTripDTO> roundTripList = new ArrayList<>();

        for(ScheduleInfo outbound : outboundSchedules){
            List<ScheduleInfo> returnOptions = scheduleInfoRepository.findReturnSchedules(returnTime, returnTime.plusHours(1), castleId);
            for(ScheduleInfo returnOption : returnOptions){
                LocalTime castleFinishTime = outbound.getArriveTime().plusHours(2);
                if(castleFinishTime.isBefore(returnOption.getDepartTime()) && castleFinishTime.isBefore(closeTime)){
                    RoundTripDTO dto = new RoundTripDTO(outbound, returnOption);
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
            System.out.println(roundTripList.get(0).getOutboundSchedule());
            System.out.println(roundTripList.get(0).getReturnSchedule());
            model.addAttribute("roundTripList", roundTripList);
            return "routeSelection";
        }
    }
}
