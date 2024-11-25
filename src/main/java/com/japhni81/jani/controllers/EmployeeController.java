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
import com.japhni81.jani.models.Employee;
import com.japhni81.jani.models.User;
import com.japhni81.jani.services.EmployeeService;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/create-employee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		try {
			employeeService.createEmployee(employee);

			return ResponseEntity.ok("Employee registered successfully!");

		} catch (UserAlreadyExistsException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	@GetMapping("/all-employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {

		return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/read-employee-by-id/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        try{
            User theCustomer = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(theCustomer);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error pour recuperer les infos d'employee");
        }
    }
	
	
	@PutMapping("/update-employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {

		employee.setId(id);

		return new ResponseEntity<Employee>(employeeService.createEmployee(employee), HttpStatus.OK);
	}

	@DeleteMapping("/delete-employee/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {

		employeeService.deleteEmployee(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}

}
