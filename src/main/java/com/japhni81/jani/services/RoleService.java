package com.japhni81.jani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.japhni81.jani.exception.RoleAlreadyExistException;
import com.japhni81.jani.exception.UserAlreadyExistsException;
import com.japhni81.jani.models.Role;
import com.japhni81.jani.models.User;
import com.japhni81.jani.repositories.RoleRepository;
import com.japhni81.jani.repositories.UserRepository;


@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;


	public List<Role> getRoles() {

		return roleRepository.findAll();
	}


	public Role createRole(Role theRole) {

		 String roleName = "ROLE_"+theRole.getName().toUpperCase();
	        Role role = new Role(roleName);
	        if (roleRepository.existsByName(roleName)){
	            throw new RoleAlreadyExistException(theRole.getName()+" role already exists");
	        }
	        return roleRepository.save(role);
	}
	

	public void deleteRole(Long roleId) {

		this.removeAllUsersFromRole(roleId);
		roleRepository.deleteById(roleId);
	}

	public Role findByName(String name) {

		return roleRepository.findByName(name).get();
	}


	public User removeUserFromRole(Long userId, Long roleId) {
		
		Optional<User> user = userRepository.findById(userId);
		Optional<Role> role = roleRepository.findById(roleId);
		
		if(role.isPresent() && role.get().getUsers().contains(user.get())) {
			
			role.get().removeUserFromRole(user.get());
			roleRepository.save(role.get());
			
			return user.get();
		}
		
		throw new UsernameNotFoundException("User Not Found!");
	}

	public User assignRoleToUser(Long userId, Long roleId) {
		
		Optional<User> user = userRepository.findById(userId);
		Optional<Role> role = roleRepository.findById(roleId);
		
		if(user.isPresent() && user.get().getRoles().contains(role.get())) {
			
			throw new UserAlreadyExistsException(
					user.get().getFirstName()+ " is already assigned to the role "+ role.get().getName() + " role");  
		}
		
		if(role.isPresent() && user.isPresent()) {
			role.get().assignRoleToUser(user.get());
			roleRepository.save(role.get());
		}else if(role.isEmpty() || user.isEmpty()) {
			
			throw new UsernameNotFoundException("User or role not found!");
		}
		
		return user.get();
	}

	public Role removeAllUsersFromRole(Long roleId) {
		
		Optional<Role> role = roleRepository.findById(roleId);
		
		role.ifPresent(Role::removeAllUsersFromRole);
		
		return roleRepository.save(role.get());
	}

}
