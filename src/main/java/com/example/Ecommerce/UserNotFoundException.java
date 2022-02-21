package com.example.Ecommerce;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super(username);
    }
}
