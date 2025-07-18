package com.mint.java_sql.controller;

import com.mint.java_sql.dto.AuthenticationDto;
import com.mint.java_sql.dto.RefreshTokenRequestDto;
import com.mint.java_sql.dto.RegisterRequestDto;
import com.mint.java_sql.dto.TokenPairDto;
import com.mint.java_sql.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDto request) {
        // Save the new user to the database and return success response.
        authService.registerEmployee(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationDto loginRequest) {
        TokenPairDto tokenPair = authService.login(loginRequest);
        return ResponseEntity.ok(tokenPair);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequestDto request) {
        TokenPairDto tokenPair = authService.refreshToken(request);
        return ResponseEntity.ok(tokenPair);
    }
}
