package com.uberaemos.ekmeksizdiettracker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;
/**
 * DailyDiet keeps the diet data for the given date. 
 * Dailydiet has a course list and a list for total 
 * nutrition values in the courses
 */
@Entity
public class DailyDiet {
	@Id
	@GeneratedValue
	private Long id;
	
	// Date in YYMMDD format that this diet object belongs to
	private String date;
	
	// Which user that this diet belongs
	@JsonIgnore
	@ManyToOne
	private User user;
	
	// Course List for this DailyDiet
	@OneToMany(mappedBy = "dailyDiet", cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<Course>();
	
	@ElementCollection
	private Map<String, Float> total = new HashMap<>();

	protected DailyDiet() {
		super();
	}

	/**
	 * DailyDiet by default is initialized with 4 default courses
	 */
	public DailyDiet(String date) {
		super();
		this.date = date;
		this.addCourse(new Course("breakfast"));
		this.addCourse(new Course("lunch"));
		this.addCourse(new Course("dinner"));
		this.addCourse(new Course("snacks"));
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

	public Map<String, Float> getTotal() {
		return total;
	}

	public void setTotal(Map<String, Float> total) {
		this.total = total;
	}

	public List<Course> getCourses() {
		return courses;
	}
	
	/**
	 * Calculates the total nutrition values in the course list.
	 * This also calls the total calculation functions for every course
	 * in course list.
	 */
	public void calculateTotal() {
		total = new HashMap<>();
		for (int i = 0; i < courses.size(); i++) {
			Course course = courses.get(i);
			if (course.getFoodList().size() > 0) {
				course.calculateTotal();	
				Set<String> nutritionKeys = course.getTotal().keySet();
				Iterator<String> nutritionIterator = nutritionKeys.iterator();
				while (nutritionIterator.hasNext()) {
					String nutritionKey = nutritionIterator.next();
					Float nutritionValue = course.getTotal().get(nutritionKey);
					if (!total.containsKey(nutritionKey)) {
						total.put(nutritionKey, nutritionValue);
					} else {
						total.put(nutritionKey, total.get(nutritionKey) + nutritionValue);
					}
				}
			}
		}
	}
	
	// Returns the course with given course name
	public Course getCourse(String courseName) {
		for (int i = 0; i < courses.size(); i++) {
			Course course = courses.get(i);
			if (course.getName().equalsIgnoreCase(courseName)) {
				return course;
			}
		}
		return null;
	}

	/** Sets the given course's owner as the current dailydiet
	 * and adds it to the current course list
	 */
	public void addCourse(Course course) {
		course.setDailyDiet(this);
		courses.add(course);
	}
	
	public void deleteCourse(Course course) {
		course.setDailyDiet(null);
		courses.remove(course);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyDiet other = (DailyDiet) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
}