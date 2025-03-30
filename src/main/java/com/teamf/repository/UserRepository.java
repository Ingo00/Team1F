package com.teamf.repository;

import com.teamf.domain.User;

/**
 * Interface for user storage operations.
 */
public interface UserRepository {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the user if found, or null otherwise
     */
    User findByUsername(String username);

    /**
     * Saves a user to the repository.
     *
     * @param user the user to save
     */
    void save(User user);

    /**
     * Deletes a user from the repository.
     *
     * @param user the user to delete
     */
    void delete(User user);
}
