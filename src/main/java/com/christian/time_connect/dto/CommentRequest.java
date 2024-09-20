package com.christian.time_connect.dto;

import jakarta.validation.constraints.NotNull;

public record CommentRequest(
        @NotNull String content
) {
}
