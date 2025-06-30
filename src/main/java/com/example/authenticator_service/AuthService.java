package com.example.authenticator_service;

public class AuthService {

    private static AuthService instance;

    private AuthService() {
        // private constructor to prevent instantiation
    }

    public static synchronized AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public String generateToken(String username) {
        return "token-for-" + username;
    }

    public boolean validateToken(String token) {
        return true;
    }
}

