package com.uberaemos.ekmeksizdiettracker.auth.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	
	Boolean existsByUsername(String username);
}
