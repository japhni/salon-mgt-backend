package com.japhni81.jani.response;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
	
	private Long id;
	private String email;
	private String phoneNumber;
	private String token;
	private String type = "Bearer";
	private List<String> roles;
	
	public JwtResponse(Long id, String email, String phoneNumber,String token, List<String> roles) {
		
		this.id = id;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.token = token;
		this.roles = roles;
	}
	
	

}
