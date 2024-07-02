package com.abreu.todo.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class TaskNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(String message) {
        super(message);
    }
}
