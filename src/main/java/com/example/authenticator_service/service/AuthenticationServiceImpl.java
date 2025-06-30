package com.example.authenticator_service.service;

import com.example.authenticator_service.adapter.AuthAdapter;
import com.example.authenticator_service.entity.User;
import com.example.authenticator_service.util.JwtUtil;
import com.example.authenticator_service.repository.UserRepository;
import com.example.authenticator_service.dtos.AuthRequest;
import com.example.authenticator_service.dtos.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthAdapter authAdapter;

    private boolean validateUserPassword(AuthRequest authRequest){
        return userRepository.findByUsername(authRequest.getUsername()).getPassword().equals(authRequest.getPassword());
    }

    @Override
    public ResponseEntity<AuthResponse> authenticateLogin(AuthRequest authRequest){
        JwtUtil jwtInstance=JwtUtil.getInstance();
        String token =null;
        if(validateUserPassword(authRequest)){
            token = jwtInstance.generateToken(authRequest.getUsername());
         }
        return createAuthResponse(token);
    }

    private ResponseEntity<AuthResponse> createAuthResponse(String token) {
        if(null==token){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(AuthResponse.builder().token(token).validToken(true).build());
    }

    @Override
    public ResponseEntity<AuthResponse> validateToken(String token) {
        try {
            JwtUtil jwtInstance=JwtUtil.getInstance();
            boolean isTokenValid=jwtInstance.validateToken(token);
            if(isTokenValid){
                return ResponseEntity.ok(AuthResponse.builder().validToken(true).build());
            }
            return ResponseEntity.ok(AuthResponse.builder().validToken(false).message("Token is expired or invalid").build());
        }catch (Exception e){
            return ResponseEntity.ok(AuthResponse.builder().validToken(false).message("Token is expired or invalid").build());
        }
    }

    @Override
    public ResponseEntity<AuthResponse> registerUser(AuthRequest authRequest) {
        User user = authAdapter.convertToUser(authRequest);
        user.setPassword(/*passwordEncoder.encode(user.getPassword())*/authRequest.getPassword());
        userRepository.save(user);

        return ResponseEntity.ok(AuthResponse.builder().message("User registered successfully!!").build());
    }

}
