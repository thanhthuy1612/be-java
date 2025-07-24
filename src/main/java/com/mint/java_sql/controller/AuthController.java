package com.mint.java_sql.controller;

import com.mint.java_sql.config.Translator;
import com.mint.java_sql.dto.request.AuthenticationDto;
import com.mint.java_sql.dto.request.RefreshTokenRequestDto;
import com.mint.java_sql.dto.request.RegisterRequestDto;
import com.mint.java_sql.dto.request.TokenPairDto;
import com.mint.java_sql.dto.response.ResponseData;
import com.mint.java_sql.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseData<String> registerUser(@Valid @RequestBody RegisterRequestDto request) {
        // Save the new user to the database and return success response.
        authService.registerEmployee(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("register.successfully"));
    }

    @PostMapping("/login")
    public ResponseData<TokenPairDto> login(@Valid @RequestBody AuthenticationDto loginRequest) {
        TokenPairDto tokenPair = authService.login(loginRequest);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("login.successfully"), tokenPair);
    }

    @PostMapping("/refresh-token")
    public ResponseData<TokenPairDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto request) {
        TokenPairDto tokenPair = authService.refreshToken(request);
        return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("refresh.token.successfully"),tokenPair);
    }
}
