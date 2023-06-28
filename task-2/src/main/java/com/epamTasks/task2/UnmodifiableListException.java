package com.epamTasks.task2;

public class UnmodifiableListException extends UnsupportedOperationException {
    public UnmodifiableListException() {
        super();
    }

    public UnmodifiableListException(String message) {
        super(message);
    }
}

