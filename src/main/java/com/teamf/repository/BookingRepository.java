package com.teamf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teamf.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Finds all bookings made by a user, fetching flight details eagerly.
     *
     * @param username the username
     * @return list of bookings with flights
     */
    @Query("SELECT b FROM Booking b JOIN FETCH b.flight WHERE LOWER(b.username) = LOWER(:username)")
    List<Booking> findByUsernameIgnoreCase(@Param("username") String username);
}
