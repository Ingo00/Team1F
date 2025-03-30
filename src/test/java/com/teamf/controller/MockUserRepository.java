package com.teamf.controller;

import com.teamf.domain.User;
import com.teamf.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class MockUserRepository implements UserRepository {
    private Map<String, User> storage = new HashMap<>();

    @Override
    public User findByUsername(String username) {
        return storage.get(username);
    }

    @Override
    public void save(User user) {
        storage.put(user.getUsername(), user);
    }

    @Override
    public void delete(User user) {
        storage.remove(user.getUsername());
    }

    public void clear() {
        storage.clear();
    }
}