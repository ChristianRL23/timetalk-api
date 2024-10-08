package com.christian.time_connect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostRequest(
        @NotBlank(message = "Title is mandatory")
        @NotNull(message = "Title is mandatory")
        String title,
        @NotBlank(message = "Description is mandatory")
        @NotNull(message = "Description is mandatory")
        String description
) {
}
