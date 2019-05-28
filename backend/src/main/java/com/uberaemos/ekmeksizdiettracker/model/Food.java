package com.uberaemos.ekmeksizdiettracker.model;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Food {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Food other = (Food) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}