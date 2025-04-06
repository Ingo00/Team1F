package com.teamf.controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teamf.entity.Booking;
import com.teamf.entity.Flight;
import com.teamf.entity.User;
import com.teamf.repository.FlightRepository;
import com.teamf.service.BookingService;

import jakarta.servlet.http.HttpSession;

/**
 * Controller for handling flight bookings and viewing bookings.
 */
@Controller
public class BookingController {

    private final BookingService bookingService;
    private final FlightRepository flightRepository;

    public BookingController(BookingService bookingService, FlightRepository flightRepository) {
        this.bookingService = bookingService;
        this.flightRepository = flightRepository;
    }

    /**
     * Displays the user's current bookings.
     */
    @GetMapping("/bookings")
    public String bookingsPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        List<Booking> bookings = bookingService.getBookingsByUser(user.getUsername());

        bookings = bookings.stream()
                .filter(Objects::nonNull)
                .filter(b -> b.getFlight() != null)
                .collect(Collectors.toList());

        System.out.println("== DEBUG BOOKINGS ==");
        System.out.println("User: " + user.getUsername());
        System.out.println("Bookings found: " + bookings.size());
        for (Booking b : bookings) {
            System.out.println(" - " + b.getFlight().getFlightNumber());
        }

        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    /**
     * Handles booking a flight from the UI and redirects to confirmation page.
     */
    @PostMapping("/book")
    public String bookFlight(@RequestParam String flightNumber,
                             @RequestParam int seats,
                             HttpSession session,
                             Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        Flight flight = flightRepository.findById(flightNumber).orElse(null);
        if (flight == null || flight.getAvailableSeats() < seats) {
            model.addAttribute("error", "Not enough seats or flight not found.");
            return "redirect:/";
        }

        flight.reduceAvailableSeats(seats);
        flightRepository.save(flight);

        Booking booking = bookingService.createBooking(user.getUsername(), flight, seats);
        model.addAttribute("booking", booking);

        // Format bookingTime in backend instead of Thymeleaf
        String formattedTime = booking.getBookingTime()
                .toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("formattedBookingTime", formattedTime);

        return "confirmation";
    }
}
