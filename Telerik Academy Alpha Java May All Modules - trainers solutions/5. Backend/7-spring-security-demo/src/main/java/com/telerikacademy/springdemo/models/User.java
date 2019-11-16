package com.telerikacademy.springdemo.models;

import javax.validation.constraints.Size;

public class User {
    @Size(min = 1, message = "Username is required")
    private String username;

    @Size(min = 1, message = "Password is required")
    private String password;

    private String passwordConfirmation;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}

