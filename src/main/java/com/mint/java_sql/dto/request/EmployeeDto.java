package com.mint.java_sql.dto.request;

import com.mint.java_sql.base.dto.BaseDto;
import com.mint.java_sql.dto.validator.EnumRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends BaseDto {
    private Long id;

    @NotBlank(message = "{username.required}")
    @Size(min = 3, max = 10, message = "{username.length}")
    private String username;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.valid}")
    private String email;

    @NotBlank(message = "{password.required}")
    @Size(min = 8, message = "{password.length}")
    private String password;

    private EnumRole role;
}
