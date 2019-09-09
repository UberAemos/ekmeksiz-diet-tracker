package com.uberaemos.ekmeksizdiettracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import javax.security.auth.login.LoginException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.exception.UsernameAlreadyExistsException;
import com.uberaemos.ekmeksizdiettracker.form.auth.JwtResponse;
import com.uberaemos.ekmeksizdiettracker.form.auth.LoginForm;
import com.uberaemos.ekmeksizdiettracker.form.auth.SignupForm;
import com.uberaemos.ekmeksizdiettracker.model.auth.RegisteredUser;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;
import com.uberaemos.ekmeksizdiettracker.model.auth.UserFactory;
import com.uberaemos.ekmeksizdiettracker.repository.auth.UserRepository;
import com.uberaemos.ekmeksizdiettracker.security.jwt.JwtProvider;

@Service
public class AuthenticationService {
	@Autowired
	private RegisteredUserService registeredUserService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
 
    @Autowired
    private JwtProvider jwtProvider;
	
    @Autowired
    private PasswordEncoder encoder;
	
	public void createUser(SignupForm signupForm) throws Exception {
        isUsernameExist(signupForm);
        
		// Creating user's account
        RegisteredUser user = (RegisteredUser) UserFactory.makeUser(signupForm);
        encodeUserPassword(user);
        userRepository.save(user);
	}
	
	public JwtResponse authenticateUser(LoginForm loginForm) throws LoginException {
		RegisteredUser user = (RegisteredUser) userRepository.findByUsername(loginForm.getUsername());
		user = (RegisteredUser) updateUser(user);
		
		Authentication authentication = createAuthentication(loginForm);
		setAuthenticationToContext(authentication);
		
        String jwt = generateJwtToken(authentication);
        return new JwtResponse(jwt);
	}
	
	public Authentication createAuthentication(LoginForm loginForm) {
		return authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginForm.getUsername(),
				loginForm.getPassword()
			)
		);
	}
	
	public User getUser(LoginForm loginForm) throws LoginException {
		try {
			return userService.findByUsername(loginForm.getUsername());
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
	}
	
	public String generateJwtToken(Authentication authentication) {
		return jwtProvider.generateJwtToken(authentication);
	}
	
	public void encodeUserPassword(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
	}
	
	public void isUsernameExist(SignupForm signupForm) {
		if (userService.isUserExist(signupForm.getUsername())) 
			throw new UsernameAlreadyExistsException("This username already exists");
	}
	
	public void setAuthenticationToContext(Authentication authentication) {
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	public User updateUser(User user) {
		if (user.isRegisteredUser()) {
			return registeredUserService.updateUserLoginDate((RegisteredUser)user);
		}
		return user;
	}
}