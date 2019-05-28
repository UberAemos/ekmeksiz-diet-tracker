package com.uberaemos.ekmeksizdiettracker.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	public void createUser(String username, String password, Set<String> strRoles, DailyDiet dailyDiet) {
        if(userRepository.existsByUsername(username)) {
            throw new RuntimeException("Fail -> Username is already taken!");
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
 
        strRoles.forEach(role -> {
          switch(role) {
          case "admin":
            Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
            roles.add(adminRole);
            
            break;
          case "pm":
                Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                roles.add(pmRole);
                
            break;
          default:
              Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
              roles.add(userRole);              
          }
        });
        
        user.setRoles(roles);
        userRepository.save(user);
	}
	
	public JwtResponse authenticateUser(String username, String password) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
		
		User user = userRepository.findByUsername(username).get();
		user.setLoginDate(new Date());
		user = userRepository.save(user);
		Boolean isAdmin = user.getRoles().contains(new Role(RoleName.ROLE_ADMIN));
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
 
        String jwt = jwtProvider.generateJwtToken(authentication);
        return new JwtResponse(jwt, isAdmin);
	}
}
