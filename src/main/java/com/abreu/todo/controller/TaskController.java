package com.abreu.todo.controller;

import com.abreu.todo.model.dto.TaskRequestDTO;
import com.abreu.todo.model.dto.TaskResponseDTO;
import com.abreu.todo.service.TaskService;
import jakarta.validation.Valid;
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

    @PatchMapping("/{id}")
    public ResponseEntity<List<TaskResponseDTO>> updatePartial(@PathVariable Long id, @RequestBody @Valid TaskRequestDTO dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @PostMapping
    public ResponseEntity<List<TaskResponseDTO>> save(@RequestBody @Valid TaskRequestDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
