package com.uberaemos.ekmeksizdiettracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.model.auth.Role;
import com.uberaemos.ekmeksizdiettracker.model.auth.RoleName;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;
import com.uberaemos.ekmeksizdiettracker.repository.auth.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		users = users.stream()
			.filter(user -> !user.getRoles().contains(new Role(RoleName.ROLE_ADMIN)))
			.collect(Collectors.toList());;
		
		return users;
	}
}
