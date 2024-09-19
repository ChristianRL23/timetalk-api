package com.christian.time_connect.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        @NotBlank String password,
        @Valid AuthCreateRoleRequest roleRequest
) {
}
