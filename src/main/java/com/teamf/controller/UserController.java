package com.teamf.controller;

import com.teamf.domain.User;
import com.teamf.repository.UserRepository;

public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    public boolean registerUser(User user) {
        if (repository.findByUsername(user.getUsername()) != null) {
            return false; // User already exists
        }
        repository.save(user);
        return true;
    }

    public boolean deleteUser(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            return false;
        }
        repository.delete(user);
        return true;
    }
}