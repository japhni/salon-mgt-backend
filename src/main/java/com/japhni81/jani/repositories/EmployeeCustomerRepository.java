package com.japhni81.jani.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.japhni81.jani.models.EmployeeCustomer;

public interface EmployeeCustomerRepository extends JpaRepository<EmployeeCustomer, Long>{
	
	
	@Query(" SELECT ec FROM EmployeeCustomer ec "
			+ "WHERE ( ((ec.createdAt >= :startDate) AND (ec.createdAt <= :endDate)) "
			+ "AND ((ec.employee_id= :id) OR (ec.customer_id= :id)) )")
    List<EmployeeCustomer> findEmployeeCustomerHistoryByDates(Long id, Date startDate, Date endDate);
	

}
