package com.example.Nusic.exception;

public class SongException extends Exception{
    public SongException(String message) {

        super(message);
    }

    public SongException(String message, Throwable cause) {
        super(message, cause);
    }
}
