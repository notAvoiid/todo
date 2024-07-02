package com.abreu.todo.model.dto;

import com.abreu.todo.model.Task;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        Integer priority,
        Boolean done
) {
    public TaskResponseDTO(Task data) {
        this(data.getId(), data.getTitle(), data.getDescription(), data.getPriority(), data.getDone());
    }
}
