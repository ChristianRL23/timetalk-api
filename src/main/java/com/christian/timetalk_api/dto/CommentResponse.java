package com.christian.timetalk_api.dto;

import java.time.LocalDateTime;

public record CommentResponse(
        UserResponse author,
        LocalDateTime creationDate,
        String content
) {
}
