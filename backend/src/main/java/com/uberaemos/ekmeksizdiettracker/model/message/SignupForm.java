package com.uberaemos.ekmeksizdiettracker.model.message;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

/**
 * Object sent by frontend for signup requests
 */
public class SignupForm {
	@NotNull(message = "Username cannot be empty")
	@Size(min = 6, max = 20, message = "Username should be between 6 and 20 characters")
    private String username;
	
	@NotNull(message = "Password cannot be empty")
	@Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    private String password;
    
    // User can register as PM or USER
	@NotNull(message = "Role set cannot be null")
	@NotEmpty(message = "Role set cannot be empty")
    private Set<String> role;
    
    /** Frontend lets user to keep a single DailyDiet before registration
     * if such a dailydiet exists form is sent with that diet info to save it
     */
    private DailyDiet diet;
 
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
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }

	public DailyDiet getDiet() {
		return diet;
	}

	public void setDiet(DailyDiet dailyDiet) {
		this.diet = dailyDiet;
	}
}
