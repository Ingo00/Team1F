package com.teamf.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamf.entity.Flight;
import com.teamf.service.FlightSearchService;

import jakarta.servlet.http.HttpSession;

/**
 * Controller for searching and viewing flight information.
 */
@Controller
public class FlightController {

    private final FlightSearchService searchService;

    public FlightController(FlightSearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Shows the homepage flight search form.
     *
     * @param session the HTTP session for verifying authentication
     * @return the index view if logged in, otherwise redirects to login
     */
    @GetMapping("/")
    public String showSearchForm(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "index";
    }

    /**
     * Searches for flights by origin and destination.
     *
     * @param origin      the departure city
     * @param destination the destination city
     * @param model       the model to populate with results
     * @return the search results view
     */
    @GetMapping("/search")
    public String searchFlights(@RequestParam String origin,
                                @RequestParam String destination,
                                Model model) {
        List<Flight> flights = searchService.searchFlights(origin, destination);
        model.addAttribute("flights", flights);
        return "search_results";
    }

    /**
     * Displays details of a specific flight by flight number.
     *
     * @param flightNumber the flight identifier
     * @param model        the model to populate with flight data
     * @return the flight details view or redirect if not found
     */
    @GetMapping("/flights/{flightNumber}")
    public String getFlightDetails(@PathVariable String flightNumber, Model model) {
        Flight flight = searchService.getFlightByNumber(flightNumber);
        if (flight == null) {
            return "redirect:/?error=FlightNotFound";
        }
        model.addAttribute("flight", flight);
        return "flight_details";
    }
}
