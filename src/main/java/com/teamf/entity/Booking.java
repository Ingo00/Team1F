package com.teamf.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity representing a booking made by a user for a specific flight.
 */
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "flight_number", referencedColumnName = "flight_number")
    private Flight flight;

    @Column(name = "seats_booked", nullable = false)
    private int seatsBooked;

    @Column(name = "booking_time", nullable = false)
    private Timestamp bookingTime;

    /**
     * Default constructor required by JPA.
     */
    public Booking() {}

    /**
     * Constructs a new booking.
     *
     * @param username     the username who made the booking
     * @param flight       the flight that was booked
     * @param seatsBooked  number of seats booked
     * @param bookingTime  timestamp of the booking
     */
    public Booking(String username, Flight flight, int seatsBooked, Timestamp bookingTime) {
        this.username = username;
        this.flight = flight;
        this.seatsBooked = seatsBooked;
        this.bookingTime = bookingTime;
    }

    public Long getId() { return id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public Flight getFlight() { return flight; }

    public void setFlight(Flight flight) { this.flight = flight; }

    public int getSeatsBooked() { return seatsBooked; }

    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }

    public Timestamp getBookingTime() { return bookingTime; }

    public void setBookingTime(Timestamp bookingTime) { this.bookingTime = bookingTime; }
}
