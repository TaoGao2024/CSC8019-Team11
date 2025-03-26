package com.team11.castleproj.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "index.html";
    }
    @RequestMapping("/itinerary")
    public String itinerary(){
        return "output.html";
    }

    @RequestMapping("/bustimes")
    public String getBusTimes(@RequestParam String agency, @RequestParam String route){
        return "output.html";
    }
}
