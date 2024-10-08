package com.christian.timetalk_api.services;

import com.christian.timetalk_api.dto.PostRequest;
import com.christian.timetalk_api.dto.PostResponse;
import com.christian.timetalk_api.entities.PostEntity;
import com.christian.timetalk_api.entities.UserEntity;
import com.christian.timetalk_api.exceptions.PostNotFoundException;
import com.christian.timetalk_api.mappers.PostMapper;
import com.christian.timetalk_api.repositories.PostRepository;
import com.christian.timetalk_api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<PostResponse> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    public PostResponse createPost(PostRequest postRequest, Authentication connectedUser) {
        UserEntity user = userRepository.findUserEntityByEmail(connectedUser.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        PostEntity post = postMapper.toPostEntity(postRequest);
        post.setUser(user);
        post.setComments(new ArrayList<>());
        post.setLikes(new ArrayList<>());
        PostEntity postCreated = postRepository.save(post);
        return postMapper.toPostResponse(postCreated);
    }

    public void deletePost(Long postId, Authentication authentication) {
        UserEntity user = userRepository.findUserEntityByEmail(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        PostEntity postFound = user.getPosts().stream()
                .filter(post -> Objects.equals(post.getId(), postId))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException("The post does not exist"));
        postRepository.deleteById(postFound.getId());
    }

    public PostResponse editPost(Long postId, PostRequest postRequest, Authentication connectedUser) {
        UserEntity user = userRepository.findUserEntityByEmail(connectedUser.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        PostEntity postFound = user.getPosts().stream()
                .filter(post -> Objects.equals(post.getId(), postId))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException("The post does not exist"));

        postFound.setTitle(postRequest.title());
        postFound.setDescription(postRequest.description());

        postRepository.save(postFound);

        return postMapper.toPostResponse(postFound);
    }
}
