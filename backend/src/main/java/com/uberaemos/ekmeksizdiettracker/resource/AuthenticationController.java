package com.uberaemos.ekmeksizdiettracker.resource;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.form.auth.JwtResponse;
import com.uberaemos.ekmeksizdiettracker.form.auth.LoginForm;
import com.uberaemos.ekmeksizdiettracker.form.auth.SignupForm;
import com.uberaemos.ekmeksizdiettracker.service.AuthenticationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService service;
 
	/**
	 * Authenticates user according to UsernamePassword Authentication
	 * @param loginRequest
	 * Login form with username and password
	 * @return
	 * JwtResponse with JWT token string
	 * @throws LoginException 
	 * Throws exception for invalid credentials
	 */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) throws LoginException {
 
        JwtResponse jwt = service.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwt);
    }
 
    /**
     * Validates then saves the user to the UserRepository
     * @param signUpRequest
     * SignupForm with username, password, user roles, and optional diet
     * @return
     * !!!
     * @throws Exception 
     */
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupForm signUpRequest) throws Exception {
        service.createUser(signUpRequest);
        return ResponseEntity.ok().body("User registered successfully!");
    }
}
