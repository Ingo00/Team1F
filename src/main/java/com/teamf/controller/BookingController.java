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
 * Controller for handling creation and viewing of flight bookings.
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
     * Displays a list of bookings made by the currently logged-in user.
     *
     * @param session the HTTP session containing the logged-in user
     * @param model   the model to populate with booking data
     * @return the bookings view or redirects to login if unauthenticated
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

        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    /**
     * Creates a booking for a selected flight and redirects to the confirmation page.
     *
     * @param flightNumber the flight number to book
     * @param seats        number of seats to book
     * @param session      the HTTP session containing the logged-in user
     * @param model        the model to pass confirmation data
     * @return the confirmation view or redirects on error
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

        String formattedTime = booking.getBookingTime()
                .toLocalDateTime()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("formattedBookingTime", formattedTime);

        return "confirmation";
    }
}
