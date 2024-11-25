package com.japhni81.jani.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.japhni81.jani.exception.UserAlreadyExistsException;
import com.japhni81.jani.models.Customer;
import com.japhni81.jani.models.Role;
import com.japhni81.jani.repositories.CustomerRepository;
import com.japhni81.jani.repositories.RoleRepository;
import com.japhni81.jani.repositories.UserRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Customer createCustomer(Customer customer) {
		
		if (userRepository.existsByEmail(customer.getEmail())) {
			throw new UserAlreadyExistsException(customer.getEmail() + " already exists");
		}
		
		if (userRepository.existsByPhoneNumber(customer.getPhoneNumber())) {
			throw new UserAlreadyExistsException(customer.getPhoneNumber() + " already exists");
		}

		customer.setPassword(customer.getPassword()== null ? passwordEncoder.encode("123456"): passwordEncoder.encode(customer.getPassword()));
		System.out.println(customer.getPassword());
		Role userRole = roleRepository.findByName("ROLE_CLIENT").get();
		customer.setRoles(Collections.singletonList(userRole));

		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();
	}

	public Customer getCustomerById(Long id) {

		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isPresent()) {

			return customer.get();
		}
		throw new UsernameNotFoundException("The Customer with id %d is not available".formatted(id));
	}

	public void deleteCustomer(Long id) {

		customerRepository.deleteById(id);
	}

}
