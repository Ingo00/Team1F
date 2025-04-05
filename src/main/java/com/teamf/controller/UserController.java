package com.teamf.controller;

import org.springframework.stereotype.Controller;

import com.teamf.entity.User;
import com.teamf.service.UserService;

/**
 * Controller for managing user registration and deletion.
 */
@Controller
public class UserController {
    private final UserService userService;

    /**
     * Constructs a new UserController with the given user service.
     *
     * @param userService the user service dependency
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user if the username is not already taken.
     *
     * @param user the user to register
     * @return true if registration is successful, false if the user already exists
     */
    public boolean registerUser(User user) {
        return userService.registerUser(user);
    }

    /**
     * Deletes a user with the given username, if they exist.
     *
     * @param username the username of the user to delete
     * @return true if the user was deleted, false if no such user exists
     */
    public boolean deleteUser(String username) {
        return userService.deleteUser(username);
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user to retrieve
     * @return the User if found, null otherwise
     */
    public User getUser(String username) {
        return userService.getUser(username);
    }
}
