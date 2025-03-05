 
package com.exception;
 
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
 
import java.util.Date;

import java.util.HashMap;

import java.util.Map;
 
@RestControllerAdvice

public class GlobalExceptionHandler {
 
    @ExceptionHandler(Exception.class)

    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception e) {

        Map<String, Object> response = new HashMap<>();

        response.put("timeStamp", new Date());

        response.put("message", "Validation failed");
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException e) {

        Map<String, Object> response = new HashMap<>();

        response.put("timeStamp", new Date());

        response.put("message", e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

}
 