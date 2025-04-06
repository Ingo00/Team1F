package com.teamf.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.teamf.entity.Booking;
import com.teamf.entity.Flight;
import com.teamf.repository.BookingRepository;

/**
 * Service responsible for handling booking-related operations.
 */
@Service
public class BookingService {

    private final BookingRepository repository;

    /**
     * Constructs the service with the given booking repository.
     *
     * @param repository the booking repository
     */
    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new booking and saves it to the database.
     *
     * @param username the username making the booking
     * @param flight the flight being booked
     * @param seats number of seats booked
     * @return the saved booking
     */
    public Booking createBooking(String username, Flight flight, int seats) {
        System.out.println("== SAVING BOOKING ==");
        System.out.println("Username: " + username);
        System.out.println("Flight: " + flight.getFlightNumber());
        System.out.println("Seats: " + seats);

        Booking booking = new Booking(username.toLowerCase(), flight, seats, new Timestamp(System.currentTimeMillis()));
        return repository.save(booking);
    }

    /**
     * Retrieves all bookings made by a specific user.
     *
     * @param username the username whose bookings to retrieve
     * @return list of bookings for that user
     */
    public List<Booking> getBookingsByUser(String username) {
        return repository.findByUsernameIgnoreCase(username);
    }
}
