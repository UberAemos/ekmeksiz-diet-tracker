package com.uberaemos.ekmeksizdiettracker.model;

import java.util.EnumMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
public abstract class Nutritious {
	
	@ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
	protected Map<Nutrient, Double> totalNutrients = new EnumMap<>(Nutrient.class);
	
	@Id
	@GeneratedValue
	private long id;
	
	protected Nutritious() {
		super();
		for (Nutrient n : Nutrient.values()) {
			totalNutrients.put(n, 0.0);
		}
	}

	protected Nutritious(Map<Nutrient, Double> totalNutrients) {
		super();
		this.totalNutrients = totalNutrients;
	}

	protected void addNutrients(Map<Nutrient, Double> nutrients) {
		for (Nutrient n : Nutrient.values()) {
			double newNutrient = this.totalNutrients.get(n) + nutrients.get(n);
			newNutrient = roundNutritiousValue(newNutrient);
			this.totalNutrients.put(n, newNutrient);
		}
	}
	
	protected void subtractNutrients(Map<Nutrient, Double> nutrients) {
		for (Nutrient n : Nutrient.values()) {
			double newNutrient = this.totalNutrients.get(n) - nutrients.get(n);
			newNutrient = roundNutritiousValue(newNutrient);
			this.totalNutrients.put(n, newNutrient);
		}
	}
	
	protected Map<Nutrient, Double> multiplyNutrient(double multiplier) {
		Map<Nutrient, Double> multipliedNutrients = new EnumMap<>(Nutrient.class);
		for (Nutrient n : Nutrient.values()) {
			double newNutrient = this.totalNutrients.get(n) * multiplier;
			newNutrient = roundNutritiousValue(newNutrient);
			multipliedNutrients.put(n, newNutrient);
		}
		return multipliedNutrients;
	}
	
	@JsonIgnore
	public abstract Nutritious highestContainer();
	
	private Double roundNutritiousValue(Double d) {
		return Math.round(d * 100.0) / 100.0;
	}

	public long getId() {return id;}
	public void setId(long id) {this.id = id;}
	public Map<Nutrient, Double> getTotalNutrients() {return totalNutrients;}
	public void setTotalNutrients(Map<Nutrient, Double> nutrients) {this.totalNutrients = nutrients;}
}