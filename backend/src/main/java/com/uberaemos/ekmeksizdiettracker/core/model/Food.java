package com.uberaemos.ekmeksizdiettracker.core.model;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uberaemos.ekmeksizdiettracker.auth.model.User;

@Entity
public class Food {

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
	
	@JsonIgnore
	@ManyToOne
	private Course course;
	
	@ElementCollection
	private Map<String, Float> nutrition;
	
	private Float serving;
	private String measure;

	protected Food() {
		
	}
	
	public Food(String name, Map<String, Float> nutrition, Float serving, String measure) {
		super();
		this.name = name;
		this.nutrition = nutrition;
		this.serving = serving;
		this.measure = measure;
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
	
	public Map<String, Float> getNutrition() {
		return nutrition;
	}

	public void setNutrition(Map<String, Float> nutrition) {
		this.nutrition = nutrition;
	}

	public Float getServing() {
		return serving;
	}

	public void setServing(Float serving) {
		this.serving = serving;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}