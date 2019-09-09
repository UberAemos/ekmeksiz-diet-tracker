package com.uberaemos.ekmeksizdiettracker.model.auth;

import com.uberaemos.ekmeksizdiettracker.form.auth.SignupForm;

public class UserFactory {

	public static User makeUser(SignupForm form) throws Exception {
		switch(form.getRole()) {
			case "USER":
				return new FreeUser(form.getUsername(), form.getPassword(), form.getDiet());
			case "PM":
				return new PremiumUser(form.getUsername(), form.getPassword(), form.getDiet());
			case "ADMIN":
				return new AdminUser(form.getUsername(), form.getPassword());
			default:
				throw new Exception("Role name is invalid");
				
		}
	}

}
