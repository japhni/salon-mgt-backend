package com.japhni81.jani.exception;

public class InvalidBookingRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidBookingRequestException(String message) {
		super(message);
	}

}
