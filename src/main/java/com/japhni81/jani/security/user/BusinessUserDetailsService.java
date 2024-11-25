package com.japhni81.jani.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.japhni81.jani.models.User;
import com.japhni81.jani.repositories.UserRepository;

@Service
public class BusinessUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmailOrPhoneNumber(username,username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found!"));
		
		return BusinessUserDetails.buildUserDetails(user);
	}

}
