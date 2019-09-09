package com.uberaemos.ekmeksizdiettracker.model.examples;

import java.util.EnumMap;
import java.util.Map;

import com.uberaemos.ekmeksizdiettracker.model.Nutrient;

public class Nutrients {
	
	public static Map<Nutrient, Double> getTwoWholeRedAppleNutrients() {
		Map<Nutrient, Double> unitNutrients = new EnumMap<>(Nutrient.class);
		unitNutrients.put(Nutrient.ENERC_KCAL, 199.28);
		unitNutrients.put(Nutrient.CHOCDF, 251.342);
		unitNutrients.put(Nutrient.FAT, 0.60);
		unitNutrients.put(Nutrient.PROCNT, 0.94);
		unitNutrients.put(Nutrient.SUGAR, 37.8);
		return unitNutrients;
	}
	
	public static Map<Nutrient, Double> getWholePizzaNutrients() {
		Map<Nutrient, Double> unitNutrients = new EnumMap<>(Nutrient.class);
		unitNutrients.put(Nutrient.ENERC_KCAL, 1211.36);
		unitNutrients.put(Nutrient.CHOCDF, 131.17);
		unitNutrients.put(Nutrient.FAT, 55.50);
		unitNutrients.put(Nutrient.PROCNT, 46.82);
		unitNutrients.put(Nutrient.SUGAR, 16.13);
		return unitNutrients;
	}
	
	public static Map<Nutrient, Double> getCanColaNutrients() {
		Map<Nutrient, Double> unitNutrients = new EnumMap<>(Nutrient.class);
		unitNutrients.put(Nutrient.ENERC_KCAL, 136.16);
		unitNutrients.put(Nutrient.CHOCDF, 35.18);
		unitNutrients.put(Nutrient.FAT, 0.07);
		unitNutrients.put(Nutrient.PROCNT, 0.26);
		unitNutrients.put(Nutrient.SUGAR, 33.0);
		return unitNutrients;
	}
	
	public static Map<Nutrient, Double> getServingOmeletteNutrients() {
		Map<Nutrient, Double> unitNutrients = new EnumMap<>(Nutrient.class);
		unitNutrients.put(Nutrient.ENERC_KCAL, 344.12);
		unitNutrients.put(Nutrient.CHOCDF, 3.34);
		unitNutrients.put(Nutrient.FAT, 27.25);
		unitNutrients.put(Nutrient.PROCNT, 2.84);
		unitNutrients.put(Nutrient.SUGAR, 2.84);
		return unitNutrients;
	}
	
	public static Map<Nutrient, Double> getServingChickenSoupNutrients() {
		Map<Nutrient, Double> unitNutrients = new EnumMap<>(Nutrient.class);
		unitNutrients.put(Nutrient.ENERC_KCAL, 72.0);
		unitNutrients.put(Nutrient.CHOCDF, 7.0);
		unitNutrients.put(Nutrient.FAT, 2.4);
		unitNutrients.put(Nutrient.PROCNT, 5.0);
		unitNutrients.put(Nutrient.SUGAR, 3.16);
		return unitNutrients;
	}
	
	public static Map<Nutrient, Double> getServingPestoPastaNutrients() {
		Map<Nutrient, Double> unitNutrients = new EnumMap<>(Nutrient.class);
		unitNutrients.put(Nutrient.ENERC_KCAL, 627.71);
		unitNutrients.put(Nutrient.CHOCDF, 97.0);
		unitNutrients.put(Nutrient.FAT, 17.27);
		unitNutrients.put(Nutrient.PROCNT, 19.27);
		unitNutrients.put(Nutrient.SUGAR, 4.64);
		return unitNutrients;
	}
}