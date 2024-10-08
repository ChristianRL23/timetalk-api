package com.christian.timetalk_api.mappers;

import com.christian.timetalk_api.dto.CommentRequest;
import com.christian.timetalk_api.dto.CommentResponse;
import com.christian.timetalk_api.entities.CommentEntity;
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
