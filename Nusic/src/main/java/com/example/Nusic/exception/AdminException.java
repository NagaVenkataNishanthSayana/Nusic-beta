package com.example.Nusic.exception;

public class AdminException extends Exception {

    public AdminException(String message) {
        super(message);
    }

    public AdminException(String message,Throwable cause) {

        super(message,cause);
    }
}
