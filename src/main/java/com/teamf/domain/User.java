package com.teamf.domain;

/**
 * Represents a user in the system.
 */
public class User {
    private final String username;
    private final String email;

    /**
     * Constructs a new user with the given username and email.
     *
     * @param username the user's username
     * @param email the user's email
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    /**
     * Returns the user's username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the user's email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}
