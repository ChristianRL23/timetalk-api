package com.christian.time_connect.handler;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Set<String> errors;
    private String path;
}
