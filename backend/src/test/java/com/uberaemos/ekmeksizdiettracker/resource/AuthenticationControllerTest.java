package com.uberaemos.ekmeksizdiettracker.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uberaemos.ekmeksizdiettracker.form.auth.SignupForm;
import com.uberaemos.ekmeksizdiettracker.service.AuthenticationService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Deprecated
public class AuthenticationControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AuthenticationService service;
	
	@Test
	public void withoutSignupForm_whenRegisterUser_thenThrowException() throws Exception {
		mvc.perform(post("/auth/signup")
			.content(asJsonString(new SignupForm()))
			.contentType(MediaType.APPLICATION_JSON))
	      	.andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
