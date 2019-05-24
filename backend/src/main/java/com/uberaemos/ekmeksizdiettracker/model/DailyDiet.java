package com.uberaemos.ekmeksizdiettracker.model;

import java.util.ArrayList;
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

@Entity
public class DailyDiet {
	@Id
	@GeneratedValue
	private Long id;
	
	private String date;
	
	@JsonIgnore
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "dailyDiet", cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<Course>();
	
	@ElementCollection
	private Map<String, Float> total;

	protected DailyDiet() {
		super();
	}

	public DailyDiet(String date) {
		super();
		this.date = date;
		this.courses.add(new Course("breakfast"));
		this.courses.add(new Course("lunch"));
		this.courses.add(new Course("dinner"));
		this.courses.add(new Course("snacks"));
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
	
	public void calculateTotal() {
		for (int i = 0; i < courses.size(); i++) {
			Course course = courses.get(i);
			course.calculateTotal();	
			if (!course.getTotal().isEmpty()) {
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
	
	public Course getCourse(String courseName) {
		for (int i = 0; i < courses.size(); i++) {
			Course course = courses.get(i);
			if (course.getName() == courseName) {
				return course;
			}
		}
		return null;
	}

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
