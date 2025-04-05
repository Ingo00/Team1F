package com.teamf.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.teamf.entity.Booking;
import com.teamf.entity.Flight;
import com.teamf.repository.BookingRepository;
import com.teamf.repository.FlightRepository;

/**
 * Service for handling bookings.
 */
@Service
public class BookingService {
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;

    /**
     * Constructs a BookingService with the given repositories.
     *
     * @param flightRepository the repository for flights
     * @param bookingRepository the repository for bookings
     */
    public BookingService(FlightRepository flightRepository, BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Books a flight for a user if seats are available.
     *
     * @param username the user booking the flight
     * @param flightNumber the flight to be booked
     * @return the booking if successful, or null if unavailable
     */
    public Booking bookFlight(String username, String flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber);
        if (flight != null && flight.getAvailableSeats() > 0) {
            flight.reduceAvailableSeats(1);
            flightRepository.save(flight);
            Booking booking = new Booking(UUID.randomUUID().toString(), username, flightNumber, LocalDateTime.now());
            bookingRepository.save(booking);
            return booking;
        }
        return null;
    }
}
