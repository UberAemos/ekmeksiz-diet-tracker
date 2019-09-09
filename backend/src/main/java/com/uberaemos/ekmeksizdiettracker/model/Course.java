package com.uberaemos.ekmeksizdiettracker.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public final class Course extends Nutritious {
	
	/** Course name ie. breakfast, lunch... */
	private String name;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "dailyDiet_id")
	private DailyDiet dailyDiet;
	
	@OneToMany(mappedBy="course", cascade = CascadeType.ALL)
	private List<Food> foodList = new ArrayList<>();
	
	protected Course() {super();}

	public Course(String name) {
		super();
		this.name = name;
	}
	
	public void addFood(Food food) {
		food.setCourse(this);
		foodList.add(food);
		addNutrients(food.getNutrients());
	}
	
	public void deleteFood(Food food) {
		food.setCourse(null);
		foodList.remove(food);
		subtractNutrients(food.getNutrients());
	}
	
	public void setInnerRelations() {
		for (Food food : foodList) {
			food.setCourse(this);
		}
	}
	
	@Override
	protected void addNutrients(Map<Nutrient, Double> nutrients) {
		super.addNutrients(nutrients);
		dailyDiet.addNutrients(nutrients);
	}
	
	@Override
	protected void subtractNutrients(Map<Nutrient, Double> nutrients) {
		super.subtractNutrients(nutrients);
		dailyDiet.subtractNutrients(nutrients);
	}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public DailyDiet getDailyDiet() {return dailyDiet;}
	public void setDailyDiet(DailyDiet dailyDiet) {this.dailyDiet = dailyDiet;}
	public List<Food> getFoodList() {return foodList;}
	public void setFoodList(List<Food> foodList) {this.foodList = foodList;}

	@JsonIgnore
	@Override
	public Nutritious highestContainer() {
		return dailyDiet.highestContainer();
	}
}