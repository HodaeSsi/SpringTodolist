package com.hodaessi.todolist.exception;

public class LengthOverException extends RuntimeException {

    public LengthOverException() {
        super();
    }

    public LengthOverException(String message) {
        super(message);
    }

    public LengthOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public LengthOverException(Throwable cause) {
        super(cause);
    }
}
