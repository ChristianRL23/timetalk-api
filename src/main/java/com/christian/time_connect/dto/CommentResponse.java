package com.christian.time_connect.dto;

import java.time.LocalDateTime;

public record CommentResponse(
        UserResponse author,
        LocalDateTime creationDate,
        String content
) {
}
