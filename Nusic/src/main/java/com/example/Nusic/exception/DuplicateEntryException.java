package com.example.Nusic.exception;

import org.hibernate.HibernateException;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }
}
