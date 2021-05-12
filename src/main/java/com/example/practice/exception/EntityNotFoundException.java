package com.example.practice.exception;

public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String message;

	public EntityNotFoundException() {
		
	}
	
	public EntityNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	

}
