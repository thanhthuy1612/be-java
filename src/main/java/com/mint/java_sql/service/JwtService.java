package com.mint.java_sql.service;

import com.mint.java_sql.dto.TokenPairDto;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Map;

@Service
public interface JwtService {

    TokenPairDto generateTokenPair(Authentication authentication);

    // Generate access token
    String generateAccessToken(Authentication authentication);

    // Generate refresh token
    String generateRefreshToken(Authentication authentication);

    String generateToken(Authentication authentication, long expirationInMs, Map<String, String> claims);

    // Validate token
    boolean validateTokenForUser(String token, UserDetails userDetails);

    boolean isValidToken(String token);

    String extractUsernameFromToken(String token);

    // Validate if the token is refresh token
    boolean isRefreshToken(String token);

    Claims extractAllClaims(String token);

    SecretKey getSignInKey();
}
