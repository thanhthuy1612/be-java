package com.mint.java_sql.service.impl;

import com.mint.java_sql.dto.request.TokenPairDto;
import com.mint.java_sql.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long jwtExpirationMs;

    @Value("${app.jwt.refresh-expiration}")
    private long refreshExpirationMs;


    @Override
    public TokenPairDto generateTokenPair(Authentication authentication) {
        String accessToken = generateAccessToken(authentication);
        String refreshToken = generateRefreshToken(authentication);

        return new TokenPairDto(accessToken, refreshToken);
    }

    @Override
    public String generateAccessToken(Authentication authentication) {
        return generateToken(authentication, jwtExpirationMs, new HashMap<>());
    }

    @Override
    public String generateRefreshToken(Authentication authentication) {
        Map<String, String> claims = new HashMap<>();
        claims.put("tokenType", "refresh");
        return generateToken(authentication, refreshExpirationMs, claims);
    }

    @Override
    public String generateToken(Authentication authentication, long expirationInMs, Map<String, String> claims) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        Date now = new Date(); // Time of token creation
        Date expiryDate = new Date(now.getTime() + expirationInMs); // Time of token expiration

        return Jwts.builder()
                .header()
                .add("typ", "JWT")
                .and()
                .subject(userPrincipal.getUsername())
                .claims(claims)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public boolean validateTokenForUser(String token, UserDetails userDetails) {
        final String username = extractUsernameFromToken(token);
        return username != null
                && username.equals(userDetails.getUsername());
    }

    @Override
    public boolean isValidToken(String token) {
        return extractAllClaims(token) != null;
    }

    @Override
    public String extractUsernameFromToken(String token) {
        Claims claims = extractAllClaims(token);

        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    @Override
    public boolean isRefreshToken(String token) {
        Claims claims = extractAllClaims(token);
        if (claims == null) {
            return false;
        }
        return "refresh".equals(claims.get("tokenType"));
    }

    @Override
    public Claims extractAllClaims(String token) {
        Claims claims = null;

        try {
            claims = Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

        return claims;
    }

    @Override
    public SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        if (keyBytes.length * 8 < 256) {
            throw new IllegalArgumentException("The specified key is not secure enough.");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
