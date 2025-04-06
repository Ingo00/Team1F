package com.teamf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamf.entity.Flight;
import com.teamf.service.FlightSearchService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FlightController {

    private final FlightSearchService searchService;

    @Autowired
    public FlightController(FlightSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/")
    public String showSearchForm(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "index"; // Only return this if user is logged in
    }


    @GetMapping("/search")
    public String searchFlights(@RequestParam String origin,
                                @RequestParam String destination,
                                Model model) {
        System.out.println("Received request for: " + origin + " -> " + destination);
        List<Flight> flights = searchService.searchFlights(origin, destination);
        System.out.println("Found flights: " + flights.size());
        model.addAttribute("flights", flights);
        return "search_results";
    }

    @GetMapping("/flights/{flightNumber}")
    public String getFlightDetails(@PathVariable String flightNumber, Model model) {
        Flight flight = searchService.getFlightByNumber(flightNumber);
        if (flight == null) {
            return "redirect:/?error=FlightNotFound";
        }
        model.addAttribute("flight", flight);
        return "flight_details"; // should match the file name flight_details.html
    }

}
