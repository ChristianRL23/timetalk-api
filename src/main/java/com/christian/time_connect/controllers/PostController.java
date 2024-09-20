package com.christian.time_connect.controllers;

import com.christian.time_connect.dto.PostRequest;
import com.christian.time_connect.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody PostRequest postRequest, Authentication authentication) {
        return new ResponseEntity<>(postService.createPost(postRequest, authentication), HttpStatus.CREATED);
    }


}
