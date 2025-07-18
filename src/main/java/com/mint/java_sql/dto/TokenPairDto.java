package com.mint.java_sql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenPairDto {
    private String accessToken;
    private String refreshToken;
}
