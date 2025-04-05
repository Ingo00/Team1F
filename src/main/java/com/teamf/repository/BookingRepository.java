package com.teamf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamf.entity.Booking;

/**
 * Interface for booking storage and retrieval.
 */

public interface BookingRepository extends JpaRepository<Booking, String> {

    /**
     * Retrieves all bookings made by a specific user.
     *
     * @param username the username
     * @return list of bookings
     */
    List<Booking> findByUsername(String username);
}
