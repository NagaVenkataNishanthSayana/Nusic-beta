package com.example.Nusic.controller.advice;

import com.example.Nusic.exception.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<Object> handleDuplicateEntryException(DuplicateEntryException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", e.getMessage());
        responseMap.put("reason", "Duplicate entry");
        responseMap.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseMap, status);
    }

    @ExceptionHandler(ForeignKeyConstraintException.class)
    public ResponseEntity<Object> handleForeignKeyConstraintException(ForeignKeyConstraintException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", e.getMessage());
        responseMap.put("reason", "Foreign key constraint violation");
        responseMap.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseMap, status);
    }

    @ExceptionHandler(DatabaseConnectionException.class)
    public ResponseEntity<Object> handleDatabaseConnectionException(DatabaseConnectionException e) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", e.getMessage());
        responseMap.put("reason", "Database connection error");
        responseMap.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseMap, status);
    }

    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<Object> handleOptimisticLockException(OptimisticLockException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", e.getMessage());
        responseMap.put("reason", "Optimistic lock error");
        responseMap.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseMap, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", e.getMessage());
        responseMap.put("reason", "Entity not found");
        responseMap.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseMap, status);
    }

    @ExceptionHandler(UnknownSqlException.class)
    public ResponseEntity<Object> handleUnknownSqlException(UnknownSqlException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", e.getMessage());
        responseMap.put("reason", "Unknown SQL error");
        responseMap.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseMap, status);
    }


    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<Object> handlePasswordMismatchException(PasswordMismatchException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", e.getMessage());
        responseMap.put("reason", "Password mismatch");
        responseMap.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseMap, status);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<Object> handleDatabaseException(DatabaseException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", e.getMessage());
        responseMap.put("reason", "Database error");
        responseMap.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseMap, status);
    }

    @ExceptionHandler(UnAuthorizedUserException.class)
    public ResponseEntity<Object> handleUnAuthorizedUserException(UnAuthorizedUserException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", e.getMessage());
        responseMap.put("reason", "Unauthorized user");
        responseMap.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(responseMap, status);
    }
}
