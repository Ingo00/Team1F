package com.teamf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamf.entity.Flight;
import com.teamf.service.FlightSearchService;

@Controller
public class FlightController {

    private final FlightSearchService searchService;

    @Autowired
    public FlightController(FlightSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/")
    public String showSearchForm() {
        return "index"; // Renders index.html
    }

    @GetMapping("/search")
    public String searchFlights(@RequestParam String origin,
                                @RequestParam String destination,
                                Model model) {
        List<Flight> flights = searchService.searchFlights(origin, destination);
        model.addAttribute("flights", flights);
        return "results"; // Renders results.html
    }
}
