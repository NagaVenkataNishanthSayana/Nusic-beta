package com.example.Nusic.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ValidationErrorResponse {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ValidationErrorResponse(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // Getters and setters
}

