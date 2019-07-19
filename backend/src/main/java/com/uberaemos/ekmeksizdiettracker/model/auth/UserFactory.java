package com.uberaemos.ekmeksizdiettracker.model.auth;

import com.uberaemos.ekmeksizdiettracker.form.auth.SignupForm;

public class UserFactory {

	public static User makeUser(SignupForm form) throws Exception {
		switch(form.getRole()) {
			case ROLE_USER:
				return new FreeUser(form.getUsername(), form.getPassword(), form.getDiet());
		case ROLE_PM:
				return new PremiumUser(form.getUsername(), form.getPassword(), form.getDiet());
		case ROLE_ADMIN:
				return new AdminUser(form.getUsername(), form.getPassword());
		default:
				throw new Exception("Role name is invalid");
				
		}
	}

}
