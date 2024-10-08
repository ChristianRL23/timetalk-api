package com.christian.timetalk_api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"firstName", "lastName", "email", "jwt", "status"})
public record AuthResponse(
        String email,
        String message,
        String jwt,
        boolean status
) {
}
