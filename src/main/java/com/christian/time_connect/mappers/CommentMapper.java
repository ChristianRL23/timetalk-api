package com.christian.time_connect.mappers;

import com.christian.time_connect.dto.CommentRequest;
import com.christian.time_connect.dto.CommentResponse;
import com.christian.time_connect.entities.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentMapper {

    private final UserMapper userMapper;

    public CommentEntity toCommentEntity(CommentRequest commentRequest) {
        return CommentEntity.builder()
                .content(commentRequest.content())
                .build();
    }

    public CommentResponse toCommentResponse(CommentEntity commentEntity) {
        return new CommentResponse(
                userMapper.toUserResponse(commentEntity.getUser()),
                commentEntity.getCreationDate(),
                commentEntity.getContent()
        );
    }
}
