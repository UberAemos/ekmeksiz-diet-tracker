package com.uberaemos.ekmeksizdiettracker.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uberaemos.ekmeksizdiettracker.model.auth.AdminUser;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;
import com.uberaemos.ekmeksizdiettracker.repository.auth.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }
    
	@Autowired
	private UserService userService;
	
	@MockBean
    private UserRepository userRepository;
	
	User testUser = new AdminUser("UberAemos", "password");
	
	@Before
	public void setup() {
		Mockito.when(userRepository.findByUsername(testUser.getUsername()))
			.thenReturn(testUser);
		
		Mockito.when(userRepository.findByUsername("error"))
			.thenReturn(null);
		
		Mockito.when(userRepository.save(testUser))
			.thenReturn(testUser);
	}
	
	@Test
	public void whenFindByUsername_thenGiveException() {
		assertThrows(Exception.class, () ->
			userService.findByUsername("error"));
	}
}
