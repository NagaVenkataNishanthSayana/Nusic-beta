package com.example.Nusic.exception;

import org.hibernate.exception.ConstraintViolationException;

public class ForeignKeyConstraintException extends RuntimeException {
    public ForeignKeyConstraintException(String message, Throwable cause) {
        super(message, cause);
    }
}
