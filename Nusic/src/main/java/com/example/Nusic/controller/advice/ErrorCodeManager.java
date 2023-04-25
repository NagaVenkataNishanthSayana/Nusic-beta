package com.example.Nusic.controller.advice;

import java.util.HashMap;
import java.util.Map;

public class ErrorCodeManager {

    private static Map<String, Integer> errorMapping = new HashMap<>();
    private static Map<String, String> errorPrefixes = new HashMap<>();

    static {
        // Add your exception classes and corresponding prefixes to the errorPrefixes map
        errorPrefixes.put("DuplicateEntryException", "d");
        errorPrefixes.put("ForeignKeyConstraintException", "f");
        errorPrefixes.put("DatabaseConnectionException", "c");
        errorPrefixes.put("OptimisticLockException", "o");
        errorPrefixes.put("EntityNotFoundException", "e");
        errorPrefixes.put("UnknownSqlException", "u");
        errorPrefixes.put("PasswordMismatchException", "p");
        errorPrefixes.put("DatabaseException", "b");
        errorPrefixes.put("UnAuthorizedUserException", "a");
    }

    public static String getErrorCode(String error) {
        int errorCode = errorMapping.getOrDefault(error, 0) + 1;
        errorMapping.put(error, errorCode);

        String prefix = errorPrefixes.getOrDefault(error, "x");
        return prefix + String.format("%02d", errorCode);
    }
}
