package com.christian.time_connect.services;

import com.christian.time_connect.dto.UserResponse;
import com.christian.time_connect.entities.UserEntity;
import com.christian.time_connect.mappers.UserMapper;
import com.christian.time_connect.repositories.UserRepository;
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
