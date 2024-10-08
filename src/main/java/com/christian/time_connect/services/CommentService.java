package com.christian.time_connect.services;

import com.christian.time_connect.dto.CommentRequest;
import com.christian.time_connect.dto.CommentResponse;
import com.christian.time_connect.entities.CommentEntity;
import com.christian.time_connect.entities.PostEntity;
import com.christian.time_connect.entities.UserEntity;
import com.christian.time_connect.exceptions.PostNotFoundException;
import com.christian.time_connect.mappers.CommentMapper;
import com.christian.time_connect.repositories.CommentRepository;
import com.christian.time_connect.repositories.PostRepository;
import com.christian.time_connect.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    public CommentResponse createComment(Long postId, CommentRequest commentRequest, Authentication connectedUser) {
        UserEntity user = userRepository.findUserEntityByEmail(connectedUser.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("The post does not exist"));


        CommentEntity comment = commentMapper.toCommentEntity(commentRequest);
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);

        return commentMapper.toCommentResponse(comment);
    }
}
