package com.uberaemos.ekmeksizdiettracker.auth.test;

import static org.junit.Assert.assertThat;

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
	
	@Test
	public void testStoreUser() {
		User user = repository.save(new User("aanilsakaryali@gmail.com",
											"my-password"));
		
		assertThat(user).isNotNull();
		
		assertThat(repository.count()).isEqualTo(1L);
		
	}
}
