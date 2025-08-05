package com.mint.java_sql.service.impl;

import com.mint.java_sql.config.Translator;
import com.mint.java_sql.dto.request.AuthenticationDto;
import com.mint.java_sql.dto.request.RefreshTokenRequestDto;
import com.mint.java_sql.dto.request.RegisterRequestDto;
import com.mint.java_sql.dto.request.TokenPairDto;
import com.mint.java_sql.entity.Employee;
import com.mint.java_sql.exception.ResourceNotFoundException;
import com.mint.java_sql.repository.EmployeeRepository;
import com.mint.java_sql.service.AuthenticationService;
import com.mint.java_sql.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public void registerEmployee(RegisterRequestDto registerRequest) {
        // Check if user with the same username already exist
        if (employeeRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ResourceNotFoundException(Translator.toLocale("username.use"));
        }

        // Create new user
        Employee user = Employee
                .builder()
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();

        employeeRepository.save(user);
    }

    @Override
    public TokenPairDto login(AuthenticationDto loginRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate Token Pair
            return jwtService.generateTokenPair(authentication);
        } catch (BadCredentialsException e) {
            throw new ResourceNotFoundException(Translator.toLocale("user.password.invalid"));
        } catch (Exception e) {
            throw new ResourceNotFoundException(Translator.toLocale("authen.failed") + e.getMessage());
        }
    }

    @Override
    public TokenPairDto refreshToken(RefreshTokenRequestDto request) {
        String refreshToken = request.getRefreshToken();
        // check if it is valid refresh token
        if (!jwtService.isRefreshToken(refreshToken)) {
            throw new ResourceNotFoundException(Translator.toLocale("invalid.refresh.token"));
        }

        String user = jwtService.extractUsernameFromToken(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user);

        if (userDetails == null) {
            throw new ResourceNotFoundException(Translator.toLocale("user.not.found"));
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        String accessToken = jwtService.generateAccessToken(authentication);
        return new TokenPairDto(accessToken, refreshToken);
    }
}
