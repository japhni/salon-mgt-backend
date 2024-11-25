package com.japhni81.jani.exception;

public class RoleAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RoleAlreadyExistException(String message) {
		
		super(message);
	}

}
