package com.japhni81.jani.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Table(name = "customers")
public class Customer extends User {

	private Long scores= 0l;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private Set<EmployeeCustomer> employeeCustomers;

	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "customerid")
	private Set<Booking> bookings;
	
}
