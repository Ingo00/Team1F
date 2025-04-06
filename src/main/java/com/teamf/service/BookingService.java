package com.teamf.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.teamf.entity.Booking;
import com.teamf.entity.Flight;
import com.teamf.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking createBooking(String username, Flight flight, int seats) {
        System.out.println("== SAVING BOOKING ==");
        System.out.println("Username: " + username);
        System.out.println("Flight: " + flight.getFlightNumber());
        System.out.println("Seats: " + seats);

        Booking booking = new Booking(username.toLowerCase(), flight, seats, new Timestamp(System.currentTimeMillis()));
        return repository.save(booking);
    }

    public List<Booking> getBookingsByUser(String username) {
        return repository.findByUsernameIgnoreCase(username);
    }
}
