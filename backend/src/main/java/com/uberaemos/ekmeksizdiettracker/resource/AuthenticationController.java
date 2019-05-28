package com.uberaemos.ekmeksizdiettracker.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.model.message.JwtResponse;
import com.uberaemos.ekmeksizdiettracker.model.message.LoginForm;
import com.uberaemos.ekmeksizdiettracker.model.message.SignupForm;
import com.uberaemos.ekmeksizdiettracker.service.AuthenticationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService service;
 
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {
 
        JwtResponse jwt = service.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(jwt);
    }
 
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupForm signUpRequest) {   
        service.createUser(signUpRequest.getUsername(), 
        		signUpRequest.getPassword(), 
        		signUpRequest.getRole(), 
        		signUpRequest.getDiet());
        return ResponseEntity.ok().body("User registered successfully!");
    }
}
