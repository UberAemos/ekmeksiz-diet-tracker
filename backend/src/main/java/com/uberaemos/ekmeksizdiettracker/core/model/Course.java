package com.uberaemos.ekmeksizdiettracker.core.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uberaemos.ekmeksizdiettracker.auth.model.User;

@Entity
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@JsonIgnore
	@ManyToOne
	private DailyDiet dailyDiet;
	
	@OneToMany(mappedBy = "course")
	private List<Food> foodList = new ArrayList<Food>();

	protected Course() {
		super();
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DailyDiet getDailyDiet() {
		return dailyDiet;
	}

	public void setDailyDiet(DailyDiet dailyDiet) {
		this.dailyDiet = dailyDiet;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void addFood(Food food) {
		this.foodList.add(food);
	}
	
	public void deleteFood(Food deleteFood) {
		for (int i = 0; i < this.foodList.size(); i++) {
			Food food = this.foodList.get(i);
			if (food.getId() == deleteFood.getId()) {
				this.foodList.remove(i);
				break;
			}
		}
	}
}
