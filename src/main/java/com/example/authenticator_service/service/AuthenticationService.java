package com.example.authenticator_service.service;

import com.example.authenticator_service.dtos.AuthRequest;
import com.example.authenticator_service.dtos.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<AuthResponse> authenticateLogin(AuthRequest authRequest);
    ResponseEntity<AuthResponse> validateToken(String token);
    ResponseEntity<AuthResponse> registerUser(AuthRequest authRequest);
}
