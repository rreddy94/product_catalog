package com.example.practice.exception;

public class EntityExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 9207165795370576595L;
	
	private String message;

	public EntityExistsException(String message) {
		super(message);
		this.message = message;
	}
	
	public EntityExistsException() {
		
	}
}