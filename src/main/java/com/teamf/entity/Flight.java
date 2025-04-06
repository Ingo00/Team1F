package com.teamf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a flight with its route, timing, and seat availability.
 */
@Entity
public class Flight {

    @Id
    @Column(name = "flight_number")
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
     * Default constructor required by JPA.
     */
    protected Flight() {
        this.flightNumber = "";
        this.origin = "";
        this.destination = "";
        this.departureTime = "";
        this.availableSeats = 0;
    }

    /**
     * Constructs a new flight.
     *
     * @param flightNumber   unique flight identifier
     * @param origin         departure city
     * @param destination    arrival city
     * @param departureTime  flight departure timestamp (as String)
     * @param availableSeats number of available seats on the flight
     */
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

    /**
     * Reduces the number of available seats.
     *
     * @param amount the number of seats to subtract
     */
    public void reduceAvailableSeats(int amount) {
        this.availableSeats -= amount;
    }
}
