package com.christian.timetalk_api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        Long id,
        UserResponse author,
        LocalDateTime creationDate,
        String title,
        String description,
        List<PostIndividualLikeResponse> likes,
        List<CommentResponse> comments
) {
}
