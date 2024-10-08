package com.christian.timetalk_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AuthCreateRoleRequest(
        @Size(max = 3, message = "The user cannot have more than 3 roles")
        @NotNull(message = "Role list is mandatory")
        @NotEmpty(message = "Role list is mandatory")
        List<String> roleListName
) {
}
