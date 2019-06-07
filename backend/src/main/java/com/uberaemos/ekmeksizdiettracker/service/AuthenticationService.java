package com.uberaemos.ekmeksizdiettracker.service;

import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import javax.naming.AuthenticationException;
import javax.security.auth.login.LoginException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.exception.UsernameAlreadyExistsException;
import com.uberaemos.ekmeksizdiettracker.model.Course;
import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.model.Food;
import com.uberaemos.ekmeksizdiettracker.model.auth.Role;
import com.uberaemos.ekmeksizdiettracker.model.auth.RoleName;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;
import com.uberaemos.ekmeksizdiettracker.model.message.JwtResponse;
import com.uberaemos.ekmeksizdiettracker.repository.auth.RoleRepository;
import com.uberaemos.ekmeksizdiettracker.repository.auth.UserRepository;
import com.uberaemos.ekmeksizdiettracker.security.jwt.JwtProvider;

@Service
public class AuthenticationService {
	
	
	@Autowired
    private AuthenticationManager authenticationManager;
 
    @Autowired
    private JwtProvider jwtProvider;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
    @Autowired
    private PasswordEncoder encoder;
	
	public void createUser(String username, String password, Set<String> strRoles, DailyDiet dailyDiet) throws UsernameAlreadyExistsException {
        if(userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException("This username already exists");
        }
        
		// Creating user's account
        User user = new User(username, encoder.encode(password));
        user.setRegisterDate(new Date());
 
        Set<Role> roles = new HashSet<>();
        if (dailyDiet != null) {
        	for (int i = 0; i < dailyDiet.getCourses().size(); i++) {
        		Course course = dailyDiet.getCourses().get(i);
        		course.setDailyDiet(dailyDiet);
        		for (int j = 0; j < course.getFoodList().size(); j++) {
        			Food food = course.getFoodList().get(j);
        			food.setCourse(course);
        		}
        	}
        	user.addDietList(dailyDiet);
        }
 
        try {
        	strRoles.forEach(role -> {
        		switch(role) {
        		case "admin":
        			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
        			roles.add(adminRole);
        			break;
        			
        		case "pm":
        			Role pmRole = roleRepository.findByName(RoleName.ROLE_PM).get();
        			roles.add(pmRole);
        			break;
        		
        		default:
        			Role userRole = roleRepository.findByName(RoleName.ROLE_USER).get();
        			roles.add(userRole);
        			break;
        		}
        	});
        } catch(NoSuchElementException e) {
        	throw new RuntimeException("User role not found");
        	
        }
        
        user.setRoles(roles);
        userRepository.save(user);
	}
	
	public JwtResponse authenticateUser(String username, String password) throws LoginException {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				username,
                password
			)
        );
		
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (!userOptional.isPresent()) {
			throw new LoginException(String.format("User with username %s does not exist", username));
		}
		
		User user = userOptional.get();
		user.setLoginDate(new Date());
		user = userRepository.save(user);
		Boolean isAdmin = user.getRoles().contains(new Role(RoleName.ROLE_ADMIN));
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
 
        String jwt = jwtProvider.generateJwtToken(authentication);
        return new JwtResponse(jwt, isAdmin);
	}
}
