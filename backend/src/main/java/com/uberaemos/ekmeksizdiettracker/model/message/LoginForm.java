package com.uberaemos.ekmeksizdiettracker.model.message;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Frontend sends object for authentication request
 */
public class LoginForm {
	@NotNull(message = "Username cannot be empty")
	@Size(min = 6, max = 20, message = "Username should be between 6 and 20 characters")
    private String username;
 
	@NotNull(message = "Password cannot be empty")
	@Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
