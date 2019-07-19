package com.uberaemos.ekmeksizdiettracker.model.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Model for registered Ekmeksiz-Diet-App user
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	/** Unique username of the user */
	private String username;
	
	/** Encoded password of the user */
	private String password;
	
	/**
	 * User role that authorizes the user in using certain services
	 */
    protected Role role;

	protected User() {
		
	}
	
	public User(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	/*
	 * Getters & Setters
	*/
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public Role getRole() {return role;}
	public void setRoles(Role role) {this.role = role;}
	
	public Boolean isRegisteredUser() {
		return this instanceof RegisteredUser;
	}
}