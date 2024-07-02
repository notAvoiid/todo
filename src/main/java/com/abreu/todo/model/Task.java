package com.abreu.todo.model;

import com.abreu.todo.model.dto.TaskRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private Boolean done;

    public Task(TaskRequestDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.priority = data.priority();
        this.done = false;
    }

    public Task(Long id, String title, String description, Integer priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.done = false;
    }
}
