package com.japhni81.jani.exception;

public class InternalServerException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InternalServerException(String message) {
		super(message);
	}

}
