package com.uberaemos.ekmeksizdiettracker;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.uberaemos.ekmeksizdiettracker.model.auth.Role;
import com.uberaemos.ekmeksizdiettracker.model.auth.RoleName;
import com.uberaemos.ekmeksizdiettracker.repository.auth.RoleRepository;
import com.uberaemos.ekmeksizdiettracker.repository.auth.UserRepository;
import com.uberaemos.ekmeksizdiettracker.service.AuthenticationService;

@SpringBootApplication
public class EkmeksizDietTrackerApplication {
	
	@Bean
	CommandLineRunner initRoleData(RoleRepository roleRepisotory) {
		return args -> {
			roleRepisotory.save(new Role(RoleName.ROLE_ADMIN));
			roleRepisotory.save(new Role(RoleName.ROLE_USER));
			roleRepisotory.save(new Role(RoleName.ROLE_PM));
		};
	}
	
	@Bean
	CommandLineRunner initData(UserRepository userRepository, AuthenticationService authenticationService) {
		return args -> {
			Set<String> roles = new HashSet<>();
			roles.add("admin");
			authenticationService.createUser("UberAemos", "123456", roles, null);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EkmeksizDietTrackerApplication.class, args);
	}

}
