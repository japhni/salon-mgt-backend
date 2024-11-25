package com.japhni81.jani.exception;

public class UserAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
		
		super(message);
	}

}
