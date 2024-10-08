package com.christian.timetalk_api.mappers;

import com.christian.timetalk_api.dto.LikeResponse;
import com.christian.timetalk_api.dto.PostIndividualLikeResponse;
import com.christian.timetalk_api.entities.LikeEntity;
import com.christian.timetalk_api.entities.PostEntity;
import com.christian.timetalk_api.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeMapper {

    private final UserMapper userMapper;

    public LikeEntity toLikeEntity(UserEntity userEntity, PostEntity postEntity) {
        return LikeEntity.builder()
                .user(userEntity)
                .post(postEntity)
                .build();
    }

    public LikeResponse toLikeResponse(PostEntity postEntity) {
        return new LikeResponse(postEntity.getId(), postEntity.getLikes().size());
    }

    public PostIndividualLikeResponse toPostIndividualLikeResponse(LikeEntity likeEntity) {
        return new PostIndividualLikeResponse(userMapper.toUserResponse(likeEntity.getUser()));
    }
}
