package com.christian.time_connect.mappers;

import com.christian.time_connect.dto.*;
import com.christian.time_connect.entities.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostMapper {

    private final UserMapper userMapper;
    private final LikeMapper likeMapper;
    private final CommentMapper commentMapper;

    public PostEntity toPostEntity(PostRequest postRequest) {
        return PostEntity.builder()
                .title(postRequest.title())
                .description(postRequest.description())
                .comments(new ArrayList<>())
                .build();
    }

    public PostResponse toPostResponse(PostEntity postEntity) {
        List<LikeResponse> likeResponseList = postEntity.getLikes()
                .stream()
                .map(likeMapper::toLikeResponse)
                .toList();

        List<CommentResponse> commentResponseList = postEntity.getComments()
                .stream()
                .map(commentMapper::toCommentResponse)
                .toList();


        return new PostResponse(
                postEntity.getId(),
                userMapper.toUserResponse(postEntity.getUser()),
                postEntity.getCreatedDate(),
                postEntity.getTitle(),
                postEntity.getDescription(),
                likeResponseList,
                commentResponseList
        );
    }
}
