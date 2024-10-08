package com.christian.timetalk_api.controllers;

import com.christian.timetalk_api.dto.AuthCreateUserRequest;
import com.christian.timetalk_api.dto.AuthLoginRequest;
import com.christian.timetalk_api.dto.AuthResponse;
import com.christian.timetalk_api.services.UserDetailServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailServiceImpl userDetailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid AuthCreateUserRequest userRequest) {
        return new ResponseEntity<>(userDetailService.createUser(userRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
        return new ResponseEntity<>(userDetailService.loginUser(userRequest), HttpStatus.OK);
    }
}
