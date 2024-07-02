package com.abreu.todo.service;

import com.abreu.todo.model.Task;
import com.abreu.todo.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public List<Task> findALl(){
        log.info("Finding all tasks!");
        return taskRepository.findAll();
    }

    @Transactional
    public Task save(Task data) {
        log.info("Saving a task!");
        return taskRepository.save(data);
    }

}
