package com.example.authenticator_service.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthResponse {
        private String token;
        private boolean validToken;
        private String message;
    }
