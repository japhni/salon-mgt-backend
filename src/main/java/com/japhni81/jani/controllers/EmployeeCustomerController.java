package com.japhni81.jani.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.japhni81.jani.models.EmployeeCustomer;
import com.japhni81.jani.services.EmployeeCustomerService;

@RestController
public class EmployeeCustomerController {

	@Autowired
	EmployeeCustomerService employeeCustomerService;

	@PostMapping("/create-employeeCustomer/{userId}")
	public ResponseEntity<EmployeeCustomer> saveEmployeeCustomer(@PathVariable Long userId, @RequestBody EmployeeCustomer employeeCustomer) {
		employeeCustomer.setCustomer_id(userId);
		return new ResponseEntity<EmployeeCustomer>(employeeCustomerService.createEmployeeCustomer(employeeCustomer),
				HttpStatus.CREATED);
	}

	@GetMapping("/all-employeeCustomers")
	public ResponseEntity<List<EmployeeCustomer>> getAllEmployeeCustomers() {

		return new ResponseEntity<List<EmployeeCustomer>>(employeeCustomerService.getAllEmployeeCustomers(),
				HttpStatus.OK);
	}

	@GetMapping("/read-employeeCustomer-by-id/{id}")
	public ResponseEntity<EmployeeCustomer> getEmployeeCustomerById(@PathVariable Long id) {

		return new ResponseEntity<EmployeeCustomer>(employeeCustomerService.getEmployeeCustomerById(id), HttpStatus.OK);
	}

	@PutMapping("/update-employeeCustomer/{id}")
	public ResponseEntity<EmployeeCustomer> updateEmployeeCustomer(@PathVariable Long id,
			@RequestBody EmployeeCustomer employeeCustomer) {

		employeeCustomer.setId(id);

		return new ResponseEntity<EmployeeCustomer>(employeeCustomerService.createEmployeeCustomer(employeeCustomer),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete-employeeCustomer/{id}")
	public ResponseEntity<HttpStatus> deleteEmployeeCustomer(@PathVariable Long id) {

		employeeCustomerService.deleteEmployeeCustomer(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}
	
	
	@GetMapping("/user-history-by-date")
    public ResponseEntity<List<EmployeeCustomer>> getHistoryUserById(
    		@RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date endDate
            ) {
	 
	 List<EmployeeCustomer> theUser= employeeCustomerService.getEmployeeCustomerHistoryByDates(userId,startDate, endDate);
	 
	 return ResponseEntity.ok(theUser);
 }

}
