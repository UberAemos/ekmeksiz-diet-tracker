package com.uberaemos.ekmeksizdiettracker.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberaemos.ekmeksizdiettracker.model.auth.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	
	Boolean existsByUsername(String username);
}