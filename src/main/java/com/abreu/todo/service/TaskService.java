package com.abreu.todo.service;

import com.abreu.todo.exceptions.TaskNotFoundException;
import com.abreu.todo.model.Task;
import com.abreu.todo.model.dto.TaskRequestDTO;
import com.abreu.todo.model.dto.TaskResponseDTO;
import com.abreu.todo.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
        Task data = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(String.format("id:%s not found!", id)));
        log.info("Finding a task by his ID");
        return new TaskResponseDTO(data);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC, "priority").and(Sort.by(Sort.Direction.ASC, "id"));
        List<Task> taskList = taskRepository.findAll(sort);
        log.info("Finding all tasks!");
        return taskList.stream().map(TaskResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public List<TaskResponseDTO> updateTask(Long id, TaskRequestDTO dto) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task taskToUpdate = existingTask.get();
            if (dto.title() != null && !(dto.title().equals(taskToUpdate.getTitle())))
                taskToUpdate.setTitle(dto.title());
            if (dto.description() != null && !(dto.description().equals(taskToUpdate.getDescription())))
                taskToUpdate.setDescription(dto.description());
            if (dto.priority() != null && !(dto.priority().equals(taskToUpdate.getPriority())))
                taskToUpdate.setPriority(dto.priority());
            if (dto.done() != null && !(dto.done().equals(taskToUpdate.getDone())))
                taskToUpdate.setDone(dto.done());

            return findAll();
        } else {
            throw new TaskNotFoundException(String.format("id:%s not found!", id));
        }
    }

    @Transactional
    public List<TaskResponseDTO> save(TaskRequestDTO data) {
        Task task = new Task(data);
        taskRepository.save(task);
        log.info("Saving a task!");
        return findAll();
    }
}
