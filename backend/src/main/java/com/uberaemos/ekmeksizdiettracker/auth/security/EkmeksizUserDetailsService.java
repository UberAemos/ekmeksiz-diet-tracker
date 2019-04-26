package com.uberaemos.ekmeksizdiettracker.auth.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.auth.repository.UserRepository;

@Service
public class EkmeksizUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = repository.findByUsername(username);

			return new EkmeksizUserDetails(user.getId(), user.getUsername(), user.getPassword(), "USER");
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
