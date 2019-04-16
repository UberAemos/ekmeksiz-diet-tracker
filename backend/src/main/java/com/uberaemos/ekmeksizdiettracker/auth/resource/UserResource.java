package com.uberaemos.ekmeksizdiettracker.auth.resource;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.auth.repository.UserRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(
			@RequestBody User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User createdUser = userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
