package com.teamf.service;

import org.springframework.stereotype.Service;

import com.teamf.entity.User;
import com.teamf.repository.UserRepository;

/**
 * Service layer for handling user-related operations.
 */
@Service
public class UserService {
    private final UserRepository repository;

    /**
     * Constructs a UserService with the given user repository.
     *
     * @param repository the user repository
     */
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Registers a new user if the username is not already in use.
     *
     * @param user the user to register
     * @return true if the user was successfully registered, false otherwise
     */
    public boolean registerUser(User user) {
        if (repository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        repository.save(user);
        return true;
    }

    /**
     * Deletes an existing user by username.
     *
     * @param username the username of the user to delete
     * @return true if the user was found and deleted, false otherwise
     */
    public boolean deleteUser(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            return false;
        }
        repository.delete(user);
        return true;
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username to look up
     * @return the User if found, or null otherwise
     */
    public User getUser(String username) {
        return repository.findByUsername(username);
    }
}
