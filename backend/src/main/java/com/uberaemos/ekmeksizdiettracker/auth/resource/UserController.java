package com.uberaemos.ekmeksizdiettracker.auth.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.auth.repository.UserRepository;
import com.uberaemos.ekmeksizdiettracker.auth.service.UserService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	private UserService service;
    
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(
			@RequestBody User user) {
		User createdUser = service.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/register/validate") 
		public ResponseEntity<Boolean> validateUser(
				@RequestBody User user) {
		Boolean exists = service.exists(user.getUsername()); 
		return new ResponseEntity<Boolean>(exists, HttpStatus.NO_CONTENT);
	}
}
