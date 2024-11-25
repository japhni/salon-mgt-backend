package com.japhni81.jani.controllers.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.japhni81.jani.exception.UserAlreadyExistsException;
import com.japhni81.jani.models.User;
import com.japhni81.jani.request.LoginRequest;
import com.japhni81.jani.response.JwtResponse;
import com.japhni81.jani.security.jwt.JwtUtils;
import com.japhni81.jani.security.user.BusinessUserDetails;
import com.japhni81.jani.services.UserService;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/register-user")
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		try {
			userService.registerUser(user);

			return ResponseEntity.ok("User registered successfully!");

		} catch (UserAlreadyExistsException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	
	@PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request){
		
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken((request.getEmail() != null) ? request.getEmail():request.getPhoneNumber(), request.getPassword()));
          SecurityContextHolder.getContext().setAuthentication(authentication);
          
        String jwt = jwtUtils.generateJwtTokenForUser(authentication);
        BusinessUserDetails userDetails = (BusinessUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();
        
        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getPhoneNumber(),
                jwt,
                roles));
    }

}
