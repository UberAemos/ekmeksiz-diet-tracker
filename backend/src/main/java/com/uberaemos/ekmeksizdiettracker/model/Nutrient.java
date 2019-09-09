package com.uberaemos.ekmeksizdiettracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Nutrient {
	ENERC_KCAL("Energy", "kcal"),
	CHOCDF("Carbs", "gr"),
	FAT("Fat", "gr"),
	PROCNT("Protein", "gr"),
	SUGAR("Sugars", "gr");
	
	public final String label;
	public final String unit;
	
	Nutrient(String label, String unit) {
		this.label = label;
		this.unit = unit;
	}
}