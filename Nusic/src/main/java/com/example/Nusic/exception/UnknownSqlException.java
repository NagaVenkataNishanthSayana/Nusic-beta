package com.example.Nusic.exception;

import org.hibernate.HibernateException;

public class UnknownSqlException extends RuntimeException {

    public UnknownSqlException(String message, Throwable cause) {
        super(message, cause);
    }

    // additional constructor for a simpler error message
    public UnknownSqlException(String message) {
        super(message);
    }
}
