package com.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 
import java.util.Date;
import java.util.Map;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
public class GlobalExceptionHandlerTest {
 
    private GlobalExceptionHandler globalExceptionHandler;
 
    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }
 
    @Test
    public void testHandleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleResourceNotFoundException(exception);
 
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().containsKey("timeStamp"));
        assertEquals("Resource not found", response.getBody().get("message"));
    }
 
    @Test
    public void testHandleResourceNotFoundExceptionWithNullMessage() {
        ResourceNotFoundException exception = new ResourceNotFoundException(null);
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleResourceNotFoundException(exception);
 
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody().containsKey("timeStamp"));
        assertEquals(null, response.getBody().get("message"));
    }
 
    @Test
    public void testHandleValidationException() {
        ValidationException exception = new ValidationException("Validation failed");
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleValidationException(exception);
 
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().containsKey("timeStamp"));
        assertEquals("Validation failed", response.getBody().get("message"));
    }
 
    @Test
    public void testHandleValidationExceptionWithEmptyMessage() {
        ValidationException exception = new ValidationException("");
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleValidationException(exception);
 
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().containsKey("timeStamp"));
        assertEquals("", response.getBody().get("message"));
    }
 
    @Test
    public void testHandleGlobalException() {
        Exception exception = new Exception("Global error");
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleGlobalException(exception);
 
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().containsKey("timeStamp"));
        assertEquals("An unexpected error occurred", response.getBody().get("message"));
    }
 
    
    @Test
    public void testHandleRuntimeException() {
        RuntimeException exception = new RuntimeException("Runtime error");
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleGlobalException(exception);
 
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().containsKey("timeStamp"));
        assertEquals("An unexpected error occurred", response.getBody().get("message"));
    }
 
    @Test
    public void testHandleCheckedException() {
        Exception exception = new Exception("Checked exception");
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleGlobalException(exception);
 
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().containsKey("timeStamp"));
        assertEquals("An unexpected error occurred", response.getBody().get("message"));
    }
}