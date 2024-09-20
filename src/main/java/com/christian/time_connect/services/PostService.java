package com.christian.time_connect.services;

import com.christian.time_connect.dto.PostRequest;
import com.christian.time_connect.entities.PostEntity;
import com.christian.time_connect.entities.UserEntity;
import com.christian.time_connect.mappers.PostMapper;
import com.christian.time_connect.repositories.PostRepository;
import com.christian.time_connect.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public Long createPost(PostRequest postRequest, Authentication connectedUser) {
        UserEntity user = userRepository.findUserEntityByEmail(connectedUser.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        PostEntity post = postMapper.toPostEntity(postRequest);
        post.setUser(user);
        return postRepository.save(post).getId();
    }
}
