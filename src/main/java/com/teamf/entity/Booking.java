package com.teamf.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a booking made by a user for a specific flight.
 */
@Entity
public class Booking {
    @Id
    private String bookingId;
    private String username;
    private String flightNumber;
    private LocalDateTime bookingDate;

    /**
     * Constructs a new Booking.
     *
     * @param bookingId the booking identifier
     * @param username the user who made the booking
     * @param flightNumber the flight number being booked
     * @param bookingDate the date the booking was made
     */

    // Constructor for JPA
    protected Booking() {
        this.bookingId = "";
        this.username = "";
        this.flightNumber = "";
        this.bookingDate = LocalDateTime.now();
    }

    public Booking(String bookingId, String username, String flightNumber, LocalDateTime bookingDate) {
        this.bookingId = bookingId;
        this.username = username;
        this.flightNumber = flightNumber;
        this.bookingDate = bookingDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUsername() {
        return username;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }
}
