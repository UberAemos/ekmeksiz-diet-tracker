package com.uberaemos.ekmeksizdiettracker.model.message;

import java.util.Set;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

public class SignupForm { 
    private String username;
    private String password;
    private Set<String> role;
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
