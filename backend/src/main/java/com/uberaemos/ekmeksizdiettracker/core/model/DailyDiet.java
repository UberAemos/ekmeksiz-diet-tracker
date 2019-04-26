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
public class DailyDiet {
	@Id
	@GeneratedValue
	private Long id;
	
	private String date;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "dailyDiet")
	private List<Course> courses = new ArrayList<Course>();

	protected DailyDiet() {
		super();
	}

	public DailyDiet(String date) {
		super();
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void addCourses(Course course) {
		this.courses.add(course);
	}
	
	public void setCourse(Course newCourse) {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getName() == newCourse.getName()) {
				courses.set(i, newCourse);
			}
		}
	}
}
