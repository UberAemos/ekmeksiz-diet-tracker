package com.uberaemos.ekmeksizdiettracker.model.auth;

import javax.persistence.Entity;

@Entity
public class AdminUser extends User {

	protected AdminUser() {}
	
	public AdminUser(String username, String password) {
		super(username, password, Role.ROLE_ADMIN);
	}

}
