package com.uberaemos.ekmeksizdiettracker.model.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String password;
	private Date registerDate;
	private Date loginDate;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<DailyDiet> dietList = new ArrayList<DailyDiet>();

	protected User() {
		
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<DailyDiet> getDietList() {
		return dietList;
	}

	public void addDietList(DailyDiet dailyDiet) {
		dailyDiet.setUser(this);
		dietList.add(dailyDiet);
	}
	
	public void deleteDiet(DailyDiet dailyDiet) {
		dailyDiet.setUser(null);
		dietList.remove(dailyDiet);
	}
	
	public Boolean containsDiet(String dietName) {
		for (int i = 0; i < dietList.size(); i++) {
			DailyDiet diet = dietList.get(i);
			if (diet.getDate().equals(dietName)) {
				return true;
			}
		}
		return false;
	}
	
	public DailyDiet getDailyDiet(String dietName) {
		for (int i = 0; i < dietList.size(); i++) {
			DailyDiet diet = dietList.get(i);
			if (diet.getDate().equals(dietName)) {
				return diet;
			}
		}
		return new DailyDiet(dietName);
	}
}