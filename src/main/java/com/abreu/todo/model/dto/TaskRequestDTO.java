package com.abreu.todo.model.dto;

public record TaskRequestDTO(
        String title,
        String description,
        Integer priority,
        Boolean done
) {
}
