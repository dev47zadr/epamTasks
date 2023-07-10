package com.epamTasks.task4.exeptions;

public class UnknownProductIndexException extends Exception {
    public UnknownProductIndexException() {
        super();
    }

    public UnknownProductIndexException(String message) {
        super(message);
    }
}
