package com.uberaemos.ekmeksizdiettracker.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.uberaemos.ekmeksizdiettracker.model.User;
import com.uberaemos.ekmeksizdiettracker.repository.UserJpaRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class UserJpaResource {

	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@PostMapping("/users/register")
	public ResponseEntity<Void> createUser(
			@RequestBody User user) {
		
		User createdUser = userJpaRepository.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/users/register/validate")
	public void validateUser(
			@RequestBody User user) {
	}
}
