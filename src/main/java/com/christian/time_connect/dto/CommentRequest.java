package com.christian.time_connect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
        @NotNull(message = "Content is mandatory")
        @NotBlank(message = "Content is mandatory")
        String content
) {
}
