package com.uberaemos.ekmeksizdiettracker.auth.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.auth.repository.UserRepository;

@Repository
@Transactional
public class UserService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserRepository repository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public Optional<User> findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
		return user;
	}
	
	public Boolean exists(String username) {
		return repository.existsByUsername(username);
	}
}
