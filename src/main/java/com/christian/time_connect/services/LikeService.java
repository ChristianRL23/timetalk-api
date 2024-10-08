package com.christian.time_connect.services;

import com.christian.time_connect.dto.LikeResponse;
import com.christian.time_connect.entities.LikeEntity;
import com.christian.time_connect.entities.PostEntity;
import com.christian.time_connect.entities.UserEntity;
import com.christian.time_connect.exceptions.ActionNotAllowedException;
import com.christian.time_connect.exceptions.PostNotFoundException;
import com.christian.time_connect.exceptions.SelfInteractionException;
import com.christian.time_connect.mappers.LikeMapper;
import com.christian.time_connect.repositories.LikeRepository;
import com.christian.time_connect.repositories.PostRepository;
import com.christian.time_connect.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    public LikeResponse addLikeToPost(Long postId, Authentication connectedUser) {
        UserEntity user = userRepository.findUserEntityByEmail(connectedUser.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("The post does not exist."));

        if(Objects.equals(post.getUser().getId(), user.getId())) {
            throw new SelfInteractionException("You cannot perform this action on your own post.");
        }



        /* TODO
        boolean userHasAlreadyCommented = post.getLikes()
                .stream()
                .anyMatch(p -> Objects.equals(p.getUser().getId(), user.getId()));

        if (userHasAlreadyCommented) {
            throw new ActionNotAllowedException("You have already liked this post.");
        }
         */

        LikeEntity likeEntity = likeMapper.toLikeEntity(user, post);
        likeRepository.save(likeEntity);

        return likeMapper.toLikeResponse(post);
    }
}
