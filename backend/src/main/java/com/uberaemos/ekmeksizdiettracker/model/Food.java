package com.uberaemos.ekmeksizdiettracker.model;

import java.util.EnumMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyEnumerated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Food extends Nutritious {
	
	private String name;
	private Double quantity;
	private String measure;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
	private Map<Nutrient, Double> unitNutrients = new EnumMap<>(Nutrient.class);
	
	protected Food() {}
	
	@JsonCreator
	public Food(
			@JsonProperty("name") String name, 
			@JsonProperty("quantity") Double quantity, 
			@JsonProperty("measure") String measure, 
			@JsonProperty("totalNutrients") Map<Nutrient, Double> totalNutrients) 
	{
		super(totalNutrients);
		this.name = name;
		this.quantity = quantity;
		this.measure = measure;
		makeUnitNutrients();
	}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getMeasure() {return measure;}
	public void setMeasure(String measure) {this.measure = measure;}
	public Double getQuantity() {return quantity;}
	public void setQuantity(Double quantity) {this.quantity = quantity;}
	public Map<Nutrient, Double> getUnitNutrients() {return unitNutrients;}
	public void setUnitNutrients(Map<Nutrient, Double> unitNutrients) {this.unitNutrients = unitNutrients;}
	public Course getCourse() {	return course;}
	public void setCourse(Course course) {this.course = course;	}

	@Override
	protected void addNutrients(Map<Nutrient, Double> nutrients) {
		super.addNutrients(nutrients);
		course.addNutrients(nutrients);
	}
	
	@Override
	protected void subtractNutrients(Map<Nutrient, Double> nutrients) {
		super.subtractNutrients(nutrients);
		course.subtractNutrients(nutrients);
	}
	
	public void addQuantity() {
		quantity++;
		addNutrients(unitNutrients);
	}
	
	public void subtractQuantity() {
		quantity--;
		subtractNutrients(unitNutrients);
	}
	
	public void makeUnitNutrients() {
		unitNutrients = multiplyNutrient(1.0 / quantity);
	}

	@JsonIgnore
	@Override
	public Nutritious highestContainer() {
		return course.highestContainer();
	}
}