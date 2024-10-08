package com.christian.time_connect.handler;

import com.christian.time_connect.exceptions.ActionNotAllowedException;
import com.christian.time_connect.exceptions.EmailAlreadyExistsException;
import com.christian.time_connect.exceptions.PostNotFoundException;
import com.christian.time_connect.exceptions.SelfInteractionException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        Set<String> errors = new HashSet<>();
        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });

        String requestURI = request.getRequestURI();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .errors(errors)
                        .path(requestURI)
                .build());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEmailAlreadyExists(EmailAlreadyExistsException exception, HttpServletRequest request) {

        HttpStatus httpStatus = HttpStatus.CONFLICT;

        Set<String> errors = new HashSet<>();
        errors.add(exception.getMessage());

        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(httpStatus.value())
                        .error(httpStatus.getReasonPhrase())
                        .errors(errors)
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentials(BadCredentialsException exception, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        Set<String> errors = new HashSet<>();
        errors.add(exception.getMessage());

        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(httpStatus.value())
                        .error(httpStatus.getReasonPhrase())
                        .errors(errors)
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePostNotFound(PostNotFoundException exception, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        Set<String> errors = new HashSet<>();
        errors.add(exception.getMessage());

        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(httpStatus.value())
                        .error(httpStatus.getReasonPhrase())
                        .errors(errors)
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(SelfInteractionException.class)
    public ResponseEntity<ExceptionResponse> handleSelfInteraction(SelfInteractionException exception, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        Set<String> errors = new HashSet<>();
        errors.add(exception.getMessage());

        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(httpStatus.value())
                        .error(httpStatus.getReasonPhrase())
                        .errors(errors)
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(ActionNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> handleActionNotAllowed(ActionNotAllowedException exception, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        Set<String> errors = new HashSet<>();
        errors.add(exception.getMessage());

        return ResponseEntity
                .status(httpStatus)
                .body(ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(httpStatus.value())
                        .error(httpStatus.getReasonPhrase())
                        .errors(errors)
                        .path(request.getRequestURI())
                        .build());
    }
}
