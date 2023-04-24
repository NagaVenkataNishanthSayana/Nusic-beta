package com.example.Nusic.exception;

public class PlayListException extends Exception{
    public PlayListException(String message) {

        super(message);
    }

    public PlayListException(String message, Throwable cause) {
        super(message, cause);
    }
}
