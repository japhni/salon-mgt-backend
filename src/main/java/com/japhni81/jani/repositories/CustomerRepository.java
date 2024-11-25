package com.japhni81.jani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japhni81.jani.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
