package com.teamf.service;

import org.springframework.stereotype.Service;

import com.teamf.entity.User;
import com.teamf.repository.UserRepository;

/**
 * Service layer responsible for user management operations.
 */
@Service
public class UserService {
    private final UserRepository repository;

    /**
     * Constructs the service with the given user repository.
     *
     * @param repository the user repository
     */
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Registers a new user if the username is not already taken.
     *
     * @param user the user to register
     * @return true if registration is successful, false otherwise
     */
    public boolean registerUser(User user) {
        if (repository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        repository.save(user);
        return true;
    }

    /**
     * Deletes a user by username.
     *
     * @param username the username of the user to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteUser(String username) {
        User user = repository.findByUsername(username);
        if (user == null) return false;
        repository.delete(user);
        return true;
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username to search
     * @return the user if found, null otherwise
     */
    public User getUser(String username) {
        return repository.findByUsername(username);
    }

    /**
     * Validates user login credentials.
     *
     * @param username the username
     * @param password the password
     * @return true if the credentials match, false otherwise
     */
    public boolean validateUserLogin(String username, String password) {
        User user = repository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
