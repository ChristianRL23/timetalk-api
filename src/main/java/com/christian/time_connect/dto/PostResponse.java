package com.christian.time_connect.dto;

import com.christian.time_connect.entities.CommentEntity;
import com.christian.time_connect.entities.LikeEntity;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        Long id,
        UserResponse author,
        LocalDateTime creationDate,
        String title,
        String description,
        List<LikeResponse> likes,
        List<CommentResponse> comments
) {
}
