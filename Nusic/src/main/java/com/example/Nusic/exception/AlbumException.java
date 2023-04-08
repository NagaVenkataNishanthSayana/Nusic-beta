package com.example.Nusic.exception;

public class AlbumException extends Exception{
    public AlbumException(String message) {
        super(message);
    }

    public AlbumException(String message, Throwable cause) {
        super(message, cause);
    }
}
