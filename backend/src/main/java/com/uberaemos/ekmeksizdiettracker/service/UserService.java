package com.uberaemos.ekmeksizdiettracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.model.auth.User;
import com.uberaemos.ekmeksizdiettracker.repository.auth.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findByUsername(String username) throws Exception {
		User user = userRepository.findByUsername(username);
		if (user != null) return user;
		else throw new Exception(String.format("User with username %s does not exist", username));
	}
	
	public Boolean isUserExist(String username) {
		return userRepository.existsByUsername(username);
	}
}