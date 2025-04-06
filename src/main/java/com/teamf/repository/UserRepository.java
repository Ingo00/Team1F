package com.teamf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamf.entity.User;

/**
 * Repository interface for accessing {@link Flight} entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the user if found, null otherwise
     */
    User findByUsername(String username);
}
