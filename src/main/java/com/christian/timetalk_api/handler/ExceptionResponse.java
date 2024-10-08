package com.christian.timetalk_api.handler;

import lombok.*;

import java.time.LocalDateTime;
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
