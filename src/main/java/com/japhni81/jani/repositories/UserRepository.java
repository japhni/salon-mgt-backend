package com.japhni81.jani.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.japhni81.jani.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmailOrPhoneNumber(String email,String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByPhoneNumber(String phoneNumber);
	
	boolean existsByEmail(String email);
	
	boolean existsByPhoneNumber(String phoneNumber);

	void deleteByEmail(String email);
	
	
	@Query("SELECT new com.japhni81.jani.models.User(firstName, lastName,otherNames, u.birthday,"
			+ " phoneNumber, email, firstAddress, secondAdress, details, roles)"
			+ "FROM User u "
			+ "WHERE u.id= :id")
	User findFewUserInfoById(Long id);

}
