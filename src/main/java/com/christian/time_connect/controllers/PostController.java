package com.christian.time_connect.controllers;

import com.christian.time_connect.dto.CommentRequest;
import com.christian.time_connect.dto.PostRequest;
import com.christian.time_connect.dto.PostResponse;
import com.christian.time_connect.services.CommentService;
import com.christian.time_connect.services.LikeService;
import com.christian.time_connect.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final LikeService likeService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody PostRequest postRequest, Authentication authentication) {
        return new ResponseEntity<>(postService.createPost(postRequest, authentication), HttpStatus.CREATED);
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<Long> addLike(@PathVariable("postId") Long postId, Authentication authentication) {
        return new ResponseEntity<>(likeService.addLikeToPost(postId, authentication), HttpStatus.OK);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Long> addComment(
            @PathVariable("postId") Long postId,
            @RequestBody CommentRequest commentRequest,
            Authentication authentication
    ) {
        return new ResponseEntity<>(commentService.createComment(postId, commentRequest, authentication), HttpStatus.CREATED);
    }
}
