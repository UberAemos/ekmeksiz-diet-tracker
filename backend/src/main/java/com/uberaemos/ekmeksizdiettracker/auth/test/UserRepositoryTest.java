package com.uberaemos.ekmeksizdiettracker.auth.test;

import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.auth.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository repository;
	
	User exampleUser = new User("aanilsakaryali@gmail.com",
			"my-password");
	
	@Test
	public void testStoreUser() {
		User user = repository.save(exampleUser);
		
		assertThat(user).isNotNull();
		
		assertThat(repository.count()).isEqualTo(1L);
		
	}
	
	@Test
	public void testFindByUsername() {
		repository.save(exampleUser);
		Optional<User> optional = repository.findByUsername(exampleUser.getUsername());
		
		assertThat(optional).isNotEmpty();
		
		assertThat(optional.get().getUsername())
			.isEqualTo(exampleUser.getUsername());
	}
}
