package com.ms.vidhyalebox.util.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ms.vidhyalebox.sharedapi.generic.APiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        logger.error("Resource Not Found: " + ex.getMessage());
        APiResponse<Object> response = new APiResponse<>(
                "error",
                ex.getMessage(),
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APiResponse<Object>> handleGenericException(Exception ex) {
    	ex.printStackTrace();
        logger.error("An unexpected error occurred: " + ex.getMessage());
        APiResponse<Object> errorResponse = new APiResponse<>(
                "error",
                "An unexpected error occurred: " + ex.getMessage(),
                null,
                null
        );
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<APiResponse<Object>> handleUnAuthrizedReq(UnauthorizedAccessException ex) {
        logger.error("Unauthorized access, invalid API key: " + ex.getMessage());
        APiResponse<Object> response = new APiResponse<>(
                "error",
                ex.getMessage(),
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(InvalidItemException.class)
    public ResponseEntity<APiResponse<Object>> handleBadItem(InvalidItemException ex) {
        logger.error("Invalid request: " + ex.getMessage());
        APiResponse<Object> response = new APiResponse<>(
                "error",
                ex.getMessage(),
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UnknownErrorException.class)
    public ResponseEntity<APiResponse<Object>> handleBadItem(UnknownErrorException ex) {
        logger.error("Unknown error: " + ex.getMessage());
        APiResponse<Object> response = new APiResponse<>(
                "error",
                ex.getMessage(),
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}