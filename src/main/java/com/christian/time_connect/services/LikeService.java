package com.christian.time_connect.services;

import com.christian.time_connect.entities.LikeEntity;
import com.christian.time_connect.entities.PostEntity;
import com.christian.time_connect.entities.UserEntity;
import com.christian.time_connect.mappers.LikeMapper;
import com.christian.time_connect.repositories.LikeRepository;
import com.christian.time_connect.repositories.PostRepository;
import com.christian.time_connect.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    public Long addLikeToPost(Long postId, Authentication connectedUser) {
        UserEntity user = userRepository.findUserEntityByEmail(connectedUser.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found."));
        LikeEntity likeEntity = likeMapper.toLikeEntity(user, post);
        return likeRepository.save(likeEntity).getId();
    }
}
