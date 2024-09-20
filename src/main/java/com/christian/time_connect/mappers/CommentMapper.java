package com.christian.time_connect.mappers;

import com.christian.time_connect.dto.CommentRequest;
import com.christian.time_connect.entities.CommentEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentMapper {
    public CommentEntity toCommentEntity(CommentRequest commentRequest) {
        return CommentEntity.builder()
                .content(commentRequest.content())
                .build();
    }
}
