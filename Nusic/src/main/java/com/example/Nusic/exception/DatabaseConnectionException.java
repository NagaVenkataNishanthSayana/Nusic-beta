package com.example.Nusic.exception;

public class DatabaseConnectionException extends RuntimeException {

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    // additional constructor for a simpler error message
    public DatabaseConnectionException(String message) {
        super(message);
    }
}
