package com.japhni81.jani.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
	
	private Long id;	

	private String firstName;

	private String lastName;

	private String otherNames;

	private LocalDate birthday;

	private String phoneNumber;

	private String email;
	
	private String password;
	
	private String firstAddress;
	
	private String secondAdress;
	
	private String details;

	public UserResponse(Long id, String firstName, String lastName, String otherNames, LocalDate birthday,
			String phoneNumber, String email, String firstAddress, String secondAdress, String details) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.otherNames = otherNames;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.firstAddress = firstAddress;
		this.secondAdress = secondAdress;
		this.details = details;
	}

	public UserResponse(String password) {
		super();
		this.password = password;
	}
	
}
