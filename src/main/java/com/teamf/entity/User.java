package com.teamf.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a user in the system.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String email;
    private String password;

    // Public no-args constructor required by JPA
    public User() {}

    /**
     * Constructs a new user with the given username, email, and password.
     *
     * @param username the user's username
     * @param email the user's email
     * @param password the user's password
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
