package com.japhni81.jani.crypto.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptAesTest {

	public static void main(String[] args) {


		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String plainPassword = "password";
		
		String encodedPassword = encoder.encode(plainPassword);
		
		
		System.out.println(encodedPassword);

	}
	

}
