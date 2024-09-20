package com.christian.time_connect.dto;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(
        @NotBlank String description
) {
}
