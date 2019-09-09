package com.uberaemos.ekmeksizdiettracker.repository.auth;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uberaemos.ekmeksizdiettracker.model.auth.AdminUser;
import com.uberaemos.ekmeksizdiettracker.model.auth.FreeUser;
import com.uberaemos.ekmeksizdiettracker.model.auth.PremiumUser;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void whenFindByUsername_thenReturnUser() {
		User adminUser = new AdminUser("Admin", "password");
		User premiumUser = new PremiumUser("Premium", "password", null);
		User freeUser = new FreeUser("Free", "password", null);
		
		userRepository.save(adminUser);
		userRepository.save(premiumUser);
		userRepository.save(freeUser);
		
		User foundAdminUser = userRepository.findByUsername(adminUser.getUsername());
		User foundPremiumUser = userRepository.findByUsername(premiumUser.getUsername());
		User foundFreeUser = userRepository.findByUsername(freeUser.getUsername());
		
		assertTrue("foundAdminUser should not be null", foundAdminUser != null);
		assertTrue("foundAdminUser and adminUser should have the same username", foundAdminUser.getUsername().equals(adminUser.getUsername()));
		
		assertTrue("foundPremiumUser should not be null", foundPremiumUser != null);
		assertTrue("foundPremiumUser and adminUser should have the same username", foundPremiumUser.getUsername().equals(premiumUser.getUsername()));
		
		assertTrue("foundFreeUser should not be null", foundFreeUser != null);
		assertTrue("foundFreeUser and freeUser should have the same username", foundFreeUser.getUsername().equals(freeUser.getUsername()));
	}
	
	@Test
	public void whenFindByUsername_nonExistentUser_thenReturnNull() {
		assertTrue("Non existent user should return null", userRepository.findByUsername("exception") == null);
	}
}
