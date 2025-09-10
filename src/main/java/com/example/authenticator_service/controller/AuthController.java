package com.example.authenticator_service.controller;

import com.example.authenticator_service.dtos.AuthResponse;
import com.example.authenticator_service.service.AuthenticationService;
import com.example.authenticator_service.service.AuthenticationServiceImpl;
import com.example.authenticator_service.util.JwtUtil;
import com.example.authenticator_service.dtos.AuthRequest;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    Logger log= Logger.getLogger(AuthController.class.getName());

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        MDC.put("service", "Auth-service");
        System.out.println("CALLING AGAIN");
        log.info("Response generated");
        return authenticationService.authenticateLogin(authRequest);
    }

    @GetMapping(value="/validate")
    public ResponseEntity<AuthResponse> validate(@RequestParam String token) {

        log.info("Validating token: "+token);
        return authenticationService.validateToken(token);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequest authRequest) {
        return authenticationService.registerUser(authRequest);
    }

}
