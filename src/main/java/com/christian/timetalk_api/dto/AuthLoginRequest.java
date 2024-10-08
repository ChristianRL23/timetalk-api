package com.christian.timetalk_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthLoginRequest (
    @Email(message = "Email is not formatted")
    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    String email,
    @Size(min = 8, message = "Password should be 8 character long minimum")
    @NotBlank(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    String password
){
}
