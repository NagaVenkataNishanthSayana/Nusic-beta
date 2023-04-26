package com.example.Nusic.exception;

public class LengthException extends RuntimeException {
    public LengthException(String message) {
        super(message);
    }

    public LengthException(String message, Throwable cause) {
        super(message, cause);
    }
}
