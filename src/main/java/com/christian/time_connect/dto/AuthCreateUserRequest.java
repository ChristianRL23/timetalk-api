package com.christian.time_connect.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record AuthCreateUserRequest(
        @NotEmpty(message = "Firstname is mandatory")
        @NotBlank(message = "Firstname is mandatory")
        String firstName,
        @NotEmpty(message = "Lastname is mandatory")
        @NotBlank(message = "Lastname is mandatory")
        String lastName,
        @Email(message = "Email is not formatted")
        @NotEmpty(message = "Email is mandatory")
        @NotBlank(message = "Email is mandatory")
        String email,
        @Size(min = 8, message = "Password should be 8 character long minimum")
        @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one number")
        @NotEmpty(message = "Password is mandatory")
        @NotBlank(message = "Password is mandatory")
        String password,
        @Valid AuthCreateRoleRequest roleRequest
) {
}
