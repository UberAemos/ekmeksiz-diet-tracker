package com.uberaemos.ekmeksizdiettracker.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.model.auth.User;
import com.uberaemos.ekmeksizdiettracker.repository.auth.UserRepository;
import com.uberaemos.ekmeksizdiettracker.service.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class AdminController {

	@Autowired
	private AdminService service;
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = service.getUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/admin/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> deleteUser(
			@PathVariable(value="id") Long id) {
		repository.deleteById(id);
		List<User> users = service.getUsers();
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}
