package com.uberaemos.ekmeksizdiettracker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@JsonIgnore
	@ManyToOne
	private DailyDiet dailyDiet;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Food> foodList = new ArrayList<Food>();
	
	@ElementCollection
	private Map<String, Float> total = new HashMap<>();

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
		food.setCourse(this);
		foodList.add(food);
	}
	
	public void deleteFood(Food deleteFood) {
		deleteFood.setCourse(null);
		foodList.remove(deleteFood);
	}

	public Map<String, Float> getTotal() {
		return total;
	}

	public void setTotal(Map<String, Float> total) {
		this.total = total;
	}
	
	public void calculateTotal() {
		total = new HashMap<>();
		for (int i = 0; i < foodList.size(); i++) {
			Food food = foodList.get(i);
			Iterator<String> nutritionIterator = food.getNutrition().keySet().iterator();
			while (nutritionIterator.hasNext()) {
				String nutritionKey = nutritionIterator.next();
				Float nutritionValue = food.getNutrition().get(nutritionKey);
				if (!total.containsKey(nutritionKey)) {
					total.put(nutritionKey, nutritionValue);
				} else {
					total.put(nutritionKey, total.get(nutritionKey) + nutritionValue);
				}
			}
		}
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
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
		Course other = (Course) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}