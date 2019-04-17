package com.uberaemos.ekmeksizdiettracker.auth.resource;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.auth.security.AuthenticationException;

@RestController
@CrossOrigin(origins ="http://localhost:3000")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	/*
	@PostMapping("${jwt.get.token.uri}")
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody TokenRequest authenticationRequest) throws AuthenticationException {
		authenticate(authenticationRequest.getUsername(), 
				authenticationRequest.getPassword());
		
		final UserDetails userDetails = inMemoryUserDetailsService.loadUserByUsername(
				authenticationRequest.getUsername());
		
		final String token = tokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new TokenResponse(token));
	}*/

	@PostMapping("/login")
	private void authenticate(@RequestBody String username, @RequestBody String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("INVALID_CREDENTIALS", e);
		}
	}
}
