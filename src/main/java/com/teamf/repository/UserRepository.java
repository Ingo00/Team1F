package com.teamf.repository;

import com.teamf.domain.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
    void delete(User user);
}