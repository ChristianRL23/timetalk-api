package com.christian.timetalk_api.services;

import com.christian.timetalk_api.dto.CommentRequest;
import com.christian.timetalk_api.dto.CommentResponse;
import com.christian.timetalk_api.entities.CommentEntity;
import com.christian.timetalk_api.entities.PostEntity;
import com.christian.timetalk_api.entities.UserEntity;
import com.christian.timetalk_api.exceptions.PostNotFoundException;
import com.christian.timetalk_api.mappers.CommentMapper;
import com.christian.timetalk_api.repositories.CommentRepository;
import com.christian.timetalk_api.repositories.PostRepository;
import com.christian.timetalk_api.repositories.UserRepository;
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
