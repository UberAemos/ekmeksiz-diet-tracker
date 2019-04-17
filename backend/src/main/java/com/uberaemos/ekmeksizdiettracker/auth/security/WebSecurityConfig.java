package com.uberaemos.ekmeksizdiettracker.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UnAuthorizedResponseAuthenticationEntryPoint unAuthorizedResponseAuthenticationEntryPoint;
	
	@Autowired
	private UserDetailsService inMemoryUserDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(inMemoryUserDetailsService)
		.passwordEncoder(passwordEncoderBean());
	}
	
	@Bean
	public PasswordEncoder passwordEncoderBean() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unAuthorizedResponseAuthenticationEntryPoint).and()
			.authorizeRequests()
			.anyRequest().authenticated();
	}
	
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		webSecurity
			.ignoring()
			.antMatchers(
					HttpMethod.POST
			)
			.antMatchers(HttpMethod.OPTIONS, "/**")
			.and()
			.ignoring()
			.antMatchers(
					HttpMethod.GET, "/"
			)
			.and()
			.ignoring()
			.antMatchers("/h2-console/**/**");
	}
}
