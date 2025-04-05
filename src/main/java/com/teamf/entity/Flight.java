package com.teamf.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a flight with route and seat availability information.
 */
@Entity
public class Flight {

    @Id
    @Column(name = "flight_number") // Matches schema.sql
    private String flightNumber;

    @Column(name = "origin") // Explicit for clarity
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_time") // Matches schema.sql
    private LocalDateTime departureTime;

    @Column(name = "available_seats") // Matches schema.sql
    private int availableSeats;

    /**
     * Required by JPA.
     */
    protected Flight() {
        this.flightNumber = "";
        this.origin = "";
        this.destination = "";
        this.departureTime = LocalDateTime.now();
        this.availableSeats = 0;
    }

    /**
     * Constructs a Flight object.
     *
     * @param flightNumber the flight number
     * @param origin the departure location
     * @param destination the arrival location
     * @param departureTime the scheduled departure time
     * @param availableSeats number of available seats
     */
    public Flight(String flightNumber, String origin, String destination,
                  LocalDateTime departureTime, int availableSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    /**
     * Reduces the number of available seats.
     *
     * @param amount the number of seats to reduce
     */
    public void reduceAvailableSeats(int amount) {
        this.availableSeats -= amount;
    }
}
