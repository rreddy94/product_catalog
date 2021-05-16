package com.example.practice.exception;

import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
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
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		String errorMessage = exception.getBindingResult().getFieldErrors().stream()
									.map(DefaultMessageSourceResolvable::getDefaultMessage)
									.collect(Collectors.joining(", "));
		
		ValidationErrorResponse response = new ValidationErrorResponse();
		response.setDescription(request.getDescription(false));
		
		response.setErrorMessage(errorMessage);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

}