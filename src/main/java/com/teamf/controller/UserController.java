package com.teamf.controller;

import com.teamf.domain.User;
import com.teamf.repository.UserRepository;

/**
 * Controller for managing user registration and deletion.
 */
public class UserController {
    private final UserRepository repository;

    /**
     * Constructs a new UserController with the given repository.
     *
     * @param repository the user repository dependency
     */
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Registers a new user if the username is not already taken.
     *
     * @param user the user to register
     * @return true if registration is successful, false if the user already exists
     */
    public boolean registerUser(User user) {
        if (repository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        repository.save(user);
        return true;
    }

    /**
     * Deletes a user with the given username, if they exist.
     *
     * @param username the username of the user to delete
     * @return true if the user was deleted, false if no such user exists
     */
    public boolean deleteUser(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            return false;
        }
        repository.delete(user);
        return true;
    }
}
