package com.mint.java_sql.service;

import com.mint.java_sql.dto.request.AuthenticationDto;
import com.mint.java_sql.dto.request.RefreshTokenRequestDto;
import com.mint.java_sql.dto.request.RegisterRequestDto;
import com.mint.java_sql.dto.request.TokenPairDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    void registerEmployee(RegisterRequestDto registerRequest);

    TokenPairDto login(AuthenticationDto loginRequest);

    TokenPairDto refreshToken(@Valid RefreshTokenRequestDto request);

}
