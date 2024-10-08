package com.christian.timetalk_api.services;

import com.christian.timetalk_api.dto.UserResponse;
import com.christian.timetalk_api.entities.UserEntity;
import com.christian.timetalk_api.mappers.UserMapper;
import com.christian.timetalk_api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse getUserProfile(Authentication connectedUser) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(connectedUser.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userMapper.toUserResponse(userEntity);
    }
}
