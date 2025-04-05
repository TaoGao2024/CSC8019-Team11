package com.team11.castleproj.Controller;

import com.team11.castleproj.Entity.CastleInfo;
import com.team11.castleproj.Repository.CastleInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private final CastleInfoRepository castleInfoRepository;
    @Autowired
    public HomeController(CastleInfoRepository castleInfoRepository) {
        this.castleInfoRepository = castleInfoRepository;
    }
    @GetMapping("/")
    public String home(){
        return "index.html";
    }
    @GetMapping("/itinerary")
    public String itinerary(){
        return "output.html";
    }
    @GetMapping("/bustimes")
    public String getBusTimes(Model model){
        List<CastleInfo> castles = castleInfoRepository.findByName("Alnwick castle");
        model.addAttribute("castles", castles);
        System.out.println("QUERY OUTPUT");
        System.out.println(castles);
        return "output.html";
    }
}

