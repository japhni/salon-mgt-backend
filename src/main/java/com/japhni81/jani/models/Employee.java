package com.japhni81.jani.models;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee extends User{

	private Date startDate;
	
	private Date endDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private Set<EmployeeCustomer> employeeCustomers;
	
}
