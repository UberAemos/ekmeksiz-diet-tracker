package com.uberaemos.ekmeksizdiettracker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uberaemos.ekmeksizdiettracker.model.auth.RegisteredUser;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;

/**
 * DailyDiet keeps the diet data for the given date. 
 * Dailydiet has a course list and a list for total 
 * nutrition values in the courses
 */

@Entity
public final class DailyDiet extends Nutritious {
	/** Date in YYMMDD format that this diet object belongs to */
	private String date;
	
	@OneToMany(mappedBy="dailyDiet", cascade = CascadeType.ALL)
	private List<Course> courseList = new ArrayList<>();
	
	/** Which user that this diet belongs to */
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private RegisteredUser user;
	
	protected DailyDiet() {
		super();
	}

	/** DailyDiet by default is initialized with 4 default courses */
	public DailyDiet(String date) {
		super();
		this.date = date;
		this.addCourse(new Course("Breakfast"));
		this.addCourse(new Course("Lunch"));
		this.addCourse(new Course("Dinner"));
		this.addCourse(new Course("Snacks"));
	}
	
	public DailyDiet(String date, Course ... courses) {
		super();
		this.date = date;
		for (Course course : courses) {
			this.addCourse(course);
		}
	}
	
	public void addCourse(Course course) {
		course.setDailyDiet(this);
		courseList.add(course);
		addNutrients(course.getNutrients());
	}
	
	public void deleteCourse(Course course) {
		course.setDailyDiet(null);
		courseList.remove(course);
		subtractNutrients(course.getNutrients());
	}
	
	public void setInnerRelations() {
		for (Course course : courseList) {
			course.setDailyDiet(this);
			course.setInnerRelations();
		}
	}

	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;	}
	public User getUser() {	return user;}
	public void setUser(RegisteredUser user) {this.user = user;}
	public List<Course> getCourseList() {return courseList;}
	public void setCourseList(List<Course> courseList) {this.courseList = courseList;}

	@JsonIgnore
	@Override
	public Nutritious highestContainer() {
		return this;
	}
}