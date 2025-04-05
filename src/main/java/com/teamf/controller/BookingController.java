package com.teamf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamf.entity.Booking;
import com.teamf.service.BookingService;

@Controller
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public String bookFlight(@RequestParam String username,
                             @RequestParam String flightNumber,
                             Model model) {
        Booking booking = bookingService.bookFlight(username, flightNumber);
        model.addAttribute("booking", booking);
        return "confirmation"; // Renders confirmation.html
    }
}
