package com.team11.castleproj.Controller;

import com.team11.castleproj.DTO.RoundTripDTO;
import com.team11.castleproj.Entity.CastleInfo;
import com.team11.castleproj.Entity.RouteInfo;
import com.team11.castleproj.Entity.ScheduleInfo;
import com.team11.castleproj.Repository.CastleInfoRepository;
import com.team11.castleproj.Repository.RouteInfoRepository;
import com.team11.castleproj.Repository.ScheduleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    private final CastleInfoRepository castleInfoRepository;
    private final RouteInfoRepository routeInfoRepository;
    private final ScheduleInfoRepository scheduleInfoRepository;

    @Autowired
    public HomeController(CastleInfoRepository castleInfoRepository, RouteInfoRepository routeInfoRepository,ScheduleInfoRepository scheduleInfoRepository) {
        this.castleInfoRepository = castleInfoRepository;
        this.scheduleInfoRepository = scheduleInfoRepository;
        this.routeInfoRepository = routeInfoRepository;
    }

    @GetMapping("/")
    public String home(){
        return "index.html";
    }

    @GetMapping("/itinerary")
    public String itinerary(){
        return "output.html";
    }

    @GetMapping("/castle")
    public String getCastleDetail(@RequestParam("name") String name, Model model) {
        List<CastleInfo> result = castleInfoRepository.findByName(name);
//        System.out.println(result.get(0).getCastleId());
//        result.ifPresentOrElse(
//                castle-> model.addAttribute("castle",castle),
//                () -> model.addAttribute("error", "Castle not found")
//        );
        return "castle_detail.html";
    }

    @GetMapping("/routes")
    public String getRoutes(@RequestParam("castleId")String castleId,@RequestParam("direction")boolean direction,Model model){
        if(direction){
            List<RouteInfo> results = routeInfoRepository.findDepartureRoutesByCastle(castleId);
            if (results.isEmpty()) {
                model.addAttribute("error", "No routes found");
            } else {
                model.addAttribute("routeList", results);
            }
        }else{
            List<RouteInfo> results = routeInfoRepository.findReturnRoutesByCastle(castleId);
            if (results.isEmpty()) {
                model.addAttribute("error", "No routes found");
            }else {
                model.addAttribute("routeList", results);
            }
        }
        return "routes.html";
    }


    @GetMapping("/schedules")
    public String selectSchedule(@RequestParam("departTime")LocalTime departTime,
                                 @RequestParam("returnTime")LocalTime returnTime,
                                 @RequestParam("castleName")String castleName,
                                 @RequestParam("noOfVisitors")int noOfVisitors,
                                 Model model) {
        List<CastleInfo> castleInfoList = castleInfoRepository.findByName(castleName);
        String castleId = castleInfoList.get(0).getCastleId();
        double entryFee = castleInfoList.get(0).getEntryFee();

        List<ScheduleInfo> outboundSchedules = scheduleInfoRepository.findOutboundSchedules(departTime,departTime.plusHours(1),castleId);

        List<RoundTripDTO> roundTripList = new ArrayList<>();
        List<Double> totalPriceList = new ArrayList<>();

        for(ScheduleInfo outbound : outboundSchedules){

            List<ScheduleInfo> returnOptions = scheduleInfoRepository.findReturnSchedules(returnTime, returnTime.plusHours(1), castleId);
            for(ScheduleInfo returnOption : returnOptions){
                if(outbound.getArriveTime().plusHours(2).isBefore(returnOption.getDepartTime())){
                    RoundTripDTO dto = new RoundTripDTO();
                    dto.setOutboundSchedule(outbound);
                    dto.setReturnSchedule(returnOption);
                    roundTripList.add(dto);

                    double routePrice = dto.getTotalPrice();
                    double totalPrice = (routePrice + entryFee) * noOfVisitors;
                    totalPriceList.add(totalPrice);
                }
            }
        }
        System.out.println(roundTripList);
        System.out.println(totalPriceList);
        model.addAttribute("roundTripList",roundTripList);
        return "schedules.html";
    }


}
