package com.example.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(value=EntityNotFoundException.class)
	public ResponseEntity<?> categoryNotFound(EntityNotFoundException exception){
		String message = exception.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value=EntityExistsException.class)
	public ResponseEntity<?> categoryExistsException(EntityExistsException exception){
		String message = exception.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}

}