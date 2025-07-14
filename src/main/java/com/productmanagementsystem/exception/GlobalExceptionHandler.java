package com.productmanagementsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.HashMap;


@ControllerAdvice
public class GlobalExceptionHandler {
	 @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
	        Map<String, String> error = new HashMap<>();
	        error.put("error", ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

	    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors()
	            .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
}
