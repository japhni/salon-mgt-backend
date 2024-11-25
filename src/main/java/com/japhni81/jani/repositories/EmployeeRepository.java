package com.japhni81.jani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japhni81.jani.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
