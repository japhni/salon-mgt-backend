package com.japhni81.jani.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.japhni81.jani.exception.UserAlreadyExistsException;
import com.japhni81.jani.models.Role;
import com.japhni81.jani.models.User;
import com.japhni81.jani.repositories.RoleRepository;
import com.japhni81.jani.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {

		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistsException(user.getEmail() + " already exists");
		}

		if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
			throw new UserAlreadyExistsException(user.getPhoneNumber() + " already exists");
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Role userRole = roleRepository.findByName("ROLE_USER").get();
		user.setRoles(Collections.singletonList(userRole));

		return userRepository.save(user);
	}

	public List<User> getUsers() {

		return userRepository.findAll();
	}

	@Transactional
	public void deleteUser(String email) {

		User theUser = getUser(email);

		if (theUser != null) {

			userRepository.deleteByEmail(email);
		}
	}

	public User getUser(String email) {

		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
	}

	public User getUserById(Long id) {

		return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
	}
	
	public User getFewUserInfosById(Long id) {
		
		if(userRepository.existsById(id)) {
			
			return userRepository.findFewUserInfoById(id);
			
		}
		
		throw new UsernameNotFoundException("User Not Found!");
		
	}

	public User updateUser(Long userId, User userData) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));

		if (userData.getFirstAddress() != null)
			user.setFirstName(userData.getFirstName());
		if (userData.getLastName() != null)
			user.setLastName(userData.getLastName());
		if (userData.getOtherNames() != null)
			user.setOtherNames(userData.getOtherNames());
		if (userData.getPhoneNumber() != null)
			user.setPhoneNumber(userData.getPhoneNumber());
		if (userData.getEmail() != null)
			user.setEmail(userData.getEmail());
		if (userData.getBirthday() != null)
			user.setBirthday(userData.getBirthday());
		if (userData.getFirstAddress() != null)
			user.setFirstAddress(userData.getFirstAddress());
		if (userData.getSecondAdress() != null)
			user.setSecondAdress(userData.getSecondAdress());
		if (userData.getDetails() != null)
			user.setDetails(userData.getDetails());

		return userRepository.save(user);
	}

	public User updateUserPassword(Long userId, User userData) {

		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));

		if (userData.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(userData.getPassword()));
		}

		return userRepository.save(user);
	}
}
