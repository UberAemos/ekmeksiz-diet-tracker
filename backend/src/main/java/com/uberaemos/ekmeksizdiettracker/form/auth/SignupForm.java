package com.uberaemos.ekmeksizdiettracker.form.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.model.auth.Role;

/**
 * Object sent by frontend for signup requests
 */
public final class SignupForm {
	@NotNull(message = "Username cannot be empty")
	@Size(min = 6, max = 20, message = "Username should be between 6 and 20 characters")
    private String username;
	
	@NotNull(message = "Password cannot be empty")
	@Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters")
    private String password;
    
    // User can register as PM or USER
	@NotNull(message = "Role cannot be null")
    private String role;
    
    /** Frontend lets user to keep a single DailyDiet before registration
     * if such a dailydiet exists form is sent with that diet info to save it
     */
    private DailyDiet diet;
    
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getRole() {return role;}
	public DailyDiet getDiet() {return diet;}
}
