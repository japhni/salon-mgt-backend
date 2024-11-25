package com.japhni81.jani.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
	
	private String email;
	
	private String phoneNumber;
	
	private String password;

}
