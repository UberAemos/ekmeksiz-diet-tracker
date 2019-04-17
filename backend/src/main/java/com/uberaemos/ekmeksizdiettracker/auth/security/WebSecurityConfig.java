package com.uberaemos.ekmeksizdiettracker.auth.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
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
