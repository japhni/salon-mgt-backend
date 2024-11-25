package com.japhni81.jani.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japhni81.jani.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByName(String string);

	boolean existsByName(String roleName);

}
