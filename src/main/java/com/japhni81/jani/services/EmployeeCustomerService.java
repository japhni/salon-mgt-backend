package com.japhni81.jani.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japhni81.jani.models.EmployeeCustomer;
import com.japhni81.jani.repositories.CustomerRepository;
import com.japhni81.jani.repositories.EmployeeCustomerRepository;

@Service
public class EmployeeCustomerService {

	@Autowired
	EmployeeCustomerRepository employeeCustomerRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	public EmployeeCustomer createEmployeeCustomer(EmployeeCustomer employeeCustomer) {

		return employeeCustomerRepository.save(employeeCustomer);
	}

	public List<EmployeeCustomer> getAllEmployeeCustomers() {

		return employeeCustomerRepository.findAll();
	}

	public EmployeeCustomer getEmployeeCustomerById(Long id) {

		Optional<EmployeeCustomer> employeeCustomer = employeeCustomerRepository.findById(id);

		if (employeeCustomer.isPresent()) {

			return employeeCustomer.get();
		}
		throw new RuntimeException("The EmployeeCustomer with id %d is not available".formatted(id));
	}
	

	public void deleteEmployeeCustomer(Long id) {

		employeeCustomerRepository.deleteById(id);
	}
	
	
public List<EmployeeCustomer> getEmployeeCustomerHistoryByDates(Long id,Date startDate, Date endDate){
		
		return employeeCustomerRepository.findEmployeeCustomerHistoryByDates(id, startDate, endDate);
	}

}
