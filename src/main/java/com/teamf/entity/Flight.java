package com.teamf.entity;

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

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_time")
    private String departureTime;

    @Column(name = "available_seats")
    private int availableSeats;

    /**
     * Required by JPA.
     */
    protected Flight() {
        this.flightNumber = "";
        this.origin = "";
        this.destination = "";
        this.departureTime = "";
        this.availableSeats = 0;
    }

    public Flight(String flightNumber, String origin, String destination,
                  String departureTime, int availableSeats) {
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

    public String getDepartureTime() {
        return departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void reduceAvailableSeats(int amount) {
        this.availableSeats -= amount;
    }
}
