package com.abreu.todo.service;

import com.abreu.todo.exceptions.TaskNotFoundException;
import com.abreu.todo.model.Task;
import com.abreu.todo.model.dto.TaskRequestDTO;
import com.abreu.todo.model.dto.TaskResponseDTO;
import com.abreu.todo.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO findById(Long id) {
        Task data = taskRepository.findById(id).orElseThrow( () -> new TaskNotFoundException(String.format("id:%s not found!", id)));
        return new TaskResponseDTO(data.getId(), data.getTitle(), data.getDescription(), data.getPriority(), data.getDone());
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> findAll(){
        List<Task> taskList = taskRepository.findAll();
        log.info("Finding all tasks!");
        return taskList.stream().map(TaskResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public TaskResponseDTO save(TaskRequestDTO data) {
        Task task = new Task(data);
        Task savedTask = taskRepository.save(task);
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO(savedTask);
        log.info("Saving a task!");

        return taskResponseDTO;
    }
}
