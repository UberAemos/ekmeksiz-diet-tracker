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
			Optional<User> user = repository.findByUsername(username);
			if (!user.isPresent()) {
				throw new UsernameNotFoundException("No user found with username: " + username);
			}
			User myUser = user.get();
			return new EkmeksizUserDetails(myUser.getId(), myUser.getUsername(), myUser.getPassword(), "USER");
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
