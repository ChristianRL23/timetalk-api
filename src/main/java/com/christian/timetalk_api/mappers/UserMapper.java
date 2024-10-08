package com.christian.timetalk_api.mappers;

import com.christian.timetalk_api.dto.UserResponse;
import com.christian.timetalk_api.entities.UserEntity;
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
