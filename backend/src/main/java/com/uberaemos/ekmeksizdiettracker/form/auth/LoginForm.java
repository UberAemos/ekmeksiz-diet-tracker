package com.uberaemos.ekmeksizdiettracker.form.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Frontend sends object for authentication request
 */
public final class LoginForm {
	@NotNull(message = "Username cannot be empty")
	@Size(min = 6, max = 20, message = "Username should be between 6 and 20 characters")
    private String username;
 
	@NotNull(message = "Password cannot be empty")
	@Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    private String password;
 
    public String getUsername() {return username;}
    public String getPassword() {return password;}
}
