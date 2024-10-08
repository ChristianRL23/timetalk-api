package com.christian.timetalk_api.services;

import com.christian.timetalk_api.dto.LikeResponse;
import com.christian.timetalk_api.entities.LikeEntity;
import com.christian.timetalk_api.entities.PostEntity;
import com.christian.timetalk_api.entities.UserEntity;
import com.christian.timetalk_api.exceptions.ActionNotAllowedException;
import com.christian.timetalk_api.exceptions.PostNotFoundException;
import com.christian.timetalk_api.exceptions.SelfInteractionException;
import com.christian.timetalk_api.mappers.LikeMapper;
import com.christian.timetalk_api.repositories.LikeRepository;
import com.christian.timetalk_api.repositories.PostRepository;
import com.christian.timetalk_api.repositories.UserRepository;
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

        boolean isLikeAlreadyGiven = likeRepository.findLikeEntityByUserId(user.getId()).isPresent();

        if (isLikeAlreadyGiven) {
            throw new ActionNotAllowedException("You have already liked this post.");
        }

        LikeEntity likeEntity = likeMapper.toLikeEntity(user, post);
        likeRepository.save(likeEntity);

        return likeMapper.toLikeResponse(post);
    }

    public void removeLike(Long postId, Authentication connectedUser) {
        UserEntity user = userRepository.findUserEntityByEmail(connectedUser.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("The post does not exist."));

        if(Objects.equals(post.getUser().getId(), user.getId())) {
            throw new SelfInteractionException("You cannot perform this action on your own post.");
        }

        LikeEntity likeEntityFound = likeRepository.findLikeEntityByUserId(user.getId())
                .orElseThrow(() -> new ActionNotAllowedException("You have not given a like to the post yet."));

        likeRepository.delete(likeEntityFound);
    }
}
