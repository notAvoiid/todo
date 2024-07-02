package com.abreu.todo.controller;

import com.abreu.todo.model.dto.TaskRequestDTO;
import com.abreu.todo.model.dto.TaskResponseDTO;
import com.abreu.todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> save(@RequestBody TaskRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(data));
    }
}
