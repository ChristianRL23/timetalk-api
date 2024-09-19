package com.christian.time_connect.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest (
    @NotBlank String email,
    @NotBlank String password
){
}
