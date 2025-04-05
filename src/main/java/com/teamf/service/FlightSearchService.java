package com.teamf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teamf.entity.Flight;
import com.teamf.repository.FlightRepository;

/**
 * Service for searching available flights.
 */
@Service
public class FlightSearchService {
    private final FlightRepository flightRepository;

    /**
     * Constructs a FlightSearchService with the given flight repository.
     *
     * @param flightRepository the repository to search from
     */
    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Searches for flights between the given origin and destination.
     *
     * @param origin the origin airport
     * @param destination the destination airport
     * @return a list of matching flights
     */
    public List<Flight> searchFlights(String origin, String destination) {
        return flightRepository.findByOriginAndDestination(origin, destination);
    }
}
