package com.christian.time_connect.mappers;

import com.christian.time_connect.dto.UserResponse;
import com.christian.time_connect.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponse toUserResponse(UserEntity user) {
        return new UserResponse(
                user.getFirstName(),
                user.getLastName()
        );
    }
}
