package com.christian.time_connect.mappers;

import com.christian.time_connect.dto.LikeResponse;
import com.christian.time_connect.entities.LikeEntity;
import com.christian.time_connect.entities.PostEntity;
import com.christian.time_connect.entities.UserEntity;
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

    public LikeResponse toLikeResponse(LikeEntity likeEntity) {
        return new LikeResponse(userMapper.toUserResponse(likeEntity.getUser()));
    }
}
