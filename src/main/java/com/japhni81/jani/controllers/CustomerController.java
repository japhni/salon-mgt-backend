package com.japhni81.jani.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.japhni81.jani.exception.UserAlreadyExistsException;
import com.japhni81.jani.models.Customer;
import com.japhni81.jani.models.User;
import com.japhni81.jani.services.CustomerService;

@RequestMapping("/customers")
@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/create-customer")
	public ResponseEntity<?> saveBooking(@RequestBody Customer customer) {
		try {
			customerService.createCustomer(customer);

			return ResponseEntity.ok("Customer registered successfully!");

		} catch (UserAlreadyExistsException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}

	}

	@GetMapping("/all-customers")
	public ResponseEntity<List<Customer>> getAllCustomer() {

		return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/read-customer-by-id/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE')or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        try{
            User theCustomer = customerService.getCustomerById(id);
            return ResponseEntity.ok(theCustomer);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error pour recuperer les infos du client");
        }
    }
	

	@PutMapping("/update-customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

		customer.setId(id);

		return new ResponseEntity<Customer>(customerService.createCustomer(customer), HttpStatus.OK);
	}

	@DeleteMapping("/delete-customer/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {

		customerService.deleteCustomer(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}
}
