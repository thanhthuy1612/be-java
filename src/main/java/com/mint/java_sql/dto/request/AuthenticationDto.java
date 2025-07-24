package com.mint.java_sql.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDto {
    @NotBlank(message = "{username.required}")
    private String username;
    @NotBlank(message = "{password.required}")
    private String password;
}
