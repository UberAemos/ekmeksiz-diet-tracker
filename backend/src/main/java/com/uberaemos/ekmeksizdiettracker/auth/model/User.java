package com.uberaemos.ekmeksizdiettracker.auth.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.uberaemos.ekmeksizdiettracker.core.model.Food;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<Food> foods = new ArrayList<Food>();

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
	
	public void addFood(Food food) {
		this.foods.add(food);
	}
	
	public void removeFood(Food food) {
		this.foods.remove(food);
	}
	
	public List<Food> getFoods() {
		return this.foods;
	}
}