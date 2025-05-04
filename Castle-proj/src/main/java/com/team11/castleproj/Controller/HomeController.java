package com.team11.castleproj.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public HomeController(CastleInfoRepository castleInfoRepository, ScheduleInfoRepository scheduleInfoRepository) {
        this.castleInfoRepository = castleInfoRepository;
        this.scheduleInfoRepository = scheduleInfoRepository;
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
                                 Model model) throws JsonProcessingException {
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

        for(ScheduleInfo outbound : outboundSchedules){
            List<ScheduleInfo> returnSchedules = scheduleInfoRepository.findReturnSchedules(returnTime, returnTime.plusHours(1), castleId);
            for(ScheduleInfo returnOption : returnSchedules){
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
            model.addAttribute("roundTripList", roundTripList);
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
