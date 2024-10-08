package com.christian.timetalk_api.dto;

public record LikeResponse(
        Long postId,
        Integer totalLikes
) {
}
