package com.abreu.todo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO(
        @NotBlank(message = "Title must not be blank!")
        @Size(min = 4, max = 30, message = "Title must be between 4 and 20 characters")
        String title,

        @NotBlank(message = "Description must not be blank!")
        @Size(min = 4, max = 30, message = "Description must be between 4 and 20 characters")
        String description,

        @NotNull(message = "Priority must not be null!")
        Integer priority,
        Boolean done
) {
}
