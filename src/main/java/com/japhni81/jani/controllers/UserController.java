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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.japhni81.jani.models.User;
import com.japhni81.jani.response.UserResponse;
import com.japhni81.jani.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all-users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> getAllUsers(){
		
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.FOUND);
	}
	
	@GetMapping("/{email}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email){
        try{
            User theUser = userService.getUser(email);
            return ResponseEntity.ok(theUser);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user");
        }
    }
	
	
	@GetMapping("/read-user-by-id/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE')or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        try{
            User theUser = userService.getUserById(id);
            return ResponseEntity.ok(theUser);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user");
        }
    }
	
	@GetMapping("/read-few-user-info-by-id/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE')or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getFewUserInfosById(@PathVariable("id") Long id){
        try{
            User theUser = userService.getFewUserInfosById(id);
            return ResponseEntity.ok(theUser);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
	
	
	@DeleteMapping("/delete/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and #email == principal.username)")
	public ResponseEntity<String> deleteUserByEmail(@PathVariable("userId") String email){
		
		try {
			
			userService.deleteUser(email);
			return ResponseEntity.ok("User deleted successfully!");
			
		} catch (UsernameNotFoundException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
		}
	}
	
	

	@PutMapping("/user/update/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,@RequestBody User user){


		User theUser = userService.updateUser(id, user);

		UserResponse userResponse = getUserResponse(theUser);

		return ResponseEntity.ok(userResponse);

	}
	
	
	@PutMapping("/user/password/update/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<UserResponse> updateUserPassword(@PathVariable Long id,@RequestBody User user){


		User theUser = userService.updateUserPassword(id, user);

		UserResponse userResponse = getUserResponse(theUser);

		return ResponseEntity.ok(userResponse);

	}
	
	
	private UserResponse getUserResponse(User user) {
		
		return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getOtherNames(), user.getBirthday(), user.getEmail(), user.getPhoneNumber(), user.getFirstAddress(), user.getSecondAdress(), user.getDetails());
	}
	
	
	
	

}
