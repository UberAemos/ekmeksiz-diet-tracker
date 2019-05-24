package com.uberaemos.ekmeksizdiettracker.model.auth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String password;
	
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
	
	public DailyDiet getDailyDiet(String dietName) {
		for (int i = 0; i < dietList.size(); i++) {
			DailyDiet diet = dietList.get(i);
			if (diet.getDate() == dietName) {
				return diet;
			}
		}
		return new DailyDiet(dietName);
	}
}