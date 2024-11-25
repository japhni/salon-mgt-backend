package com.japhni81.jani.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.japhni81.jani.exception.UserAlreadyExistsException;
import com.japhni81.jani.models.Employee;
import com.japhni81.jani.models.Role;
import com.japhni81.jani.repositories.EmployeeRepository;
import com.japhni81.jani.repositories.RoleRepository;
import com.japhni81.jani.repositories.UserRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Employee createEmployee(Employee employee) {
		
		if (userRepository.existsByEmail(employee.getEmail())) {
			throw new UserAlreadyExistsException(employee.getEmail() + " already exists");
		}
		
		if (userRepository.existsByPhoneNumber(employee.getPhoneNumber())) {
			throw new UserAlreadyExistsException(employee.getPhoneNumber() + " already exists");
		}

		employee.setPassword(employee.getPassword()== null ? passwordEncoder.encode("123456"): passwordEncoder.encode(employee.getPassword()));
		System.out.println(employee.getPassword());
		Role userRole = roleRepository.findByName("ROLE_EMPLOYEE").get();
		employee.setRoles(Collections.singletonList(userRole));

		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {

		Optional<Employee> employee = employeeRepository.findById(id);

		if (employee.isPresent()) {

			return employee.get();
		}
		throw new UsernameNotFoundException("The Employee with id %d is not available".formatted(id));
	}

	public void deleteEmployee(Long id) {

		employeeRepository.deleteById(id);
	}
}
