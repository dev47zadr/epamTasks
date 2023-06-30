package com.epamTasks.task3;

public class DuplicateValueException extends RuntimeException {
    public DuplicateValueException() {
        super("Duplicate value detected.");
    }
}

