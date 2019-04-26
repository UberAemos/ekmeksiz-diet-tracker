package com.uberaemos.ekmeksizdiettracker.auth.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.uberaemos.ekmeksizdiettracker.core.model.Course;
import com.uberaemos.ekmeksizdiettracker.core.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.core.model.Food;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String password;
	
	@OneToMany(mappedBy="user")
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
		this.dietList.add(dailyDiet);
	}
	
	public void setDailyDiet(DailyDiet newDiet) {
		for (int i = 0; i < dietList.size(); i++) {
			if (dietList.get(i).getDate() == newDiet.getDate()) {
				dietList.set(i, newDiet);
			}
		}
	}
	
	
}