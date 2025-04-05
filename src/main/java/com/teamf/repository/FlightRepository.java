package com.teamf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamf.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, String> {

    /**
     * Finds all flights between two airports.
     *
     * @param origin the departure airport
     * @param destination the arrival airport
     * @return list of flights matching the criteria
     */
    List<Flight> findByOriginAndDestination(String origin, String destination);

    /**
     * Finds a flight by its flight number.
     *
     * @param flightNumber the flight number
     * @return the flight if found, or null otherwise
     */
    Flight findByFlightNumber(String flightNumber);
}
