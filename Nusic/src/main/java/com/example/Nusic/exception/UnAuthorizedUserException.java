package com.example.Nusic.exception;

public class UnAuthorizedUserException extends RuntimeException{

    public UnAuthorizedUserException(String message) {
        super(message);
    }

    public UnAuthorizedUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
