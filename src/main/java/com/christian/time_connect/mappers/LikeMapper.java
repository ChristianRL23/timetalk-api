package com.christian.time_connect.mappers;

import com.christian.time_connect.entities.LikeEntity;
import com.christian.time_connect.entities.PostEntity;
import com.christian.time_connect.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeMapper {
    public LikeEntity toLikeEntity(UserEntity userEntity, PostEntity postEntity) {
        return LikeEntity.builder()
                .user(userEntity)
                .post(postEntity)
                .build();
    }
}
