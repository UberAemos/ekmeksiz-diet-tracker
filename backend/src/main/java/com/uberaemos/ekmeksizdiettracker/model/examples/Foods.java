package com.uberaemos.ekmeksizdiettracker.model.examples;

import com.uberaemos.ekmeksizdiettracker.model.Food;

public class Foods {

	public static Food getWholeRedApple() {
		Food food = new Food("Red Apple", 2.0, "Whole", Nutrients.getTwoWholeRedAppleNutrients());
		return food;
	}
	
	public static Food getWholePizza() {
		Food food = new Food("Pizza", 1.0, "Whole", Nutrients.getWholePizzaNutrients());
		return food;
	}
	
	public static Food getCanCola() {
		Food food = new Food("Cola", 1.0, "Can", Nutrients.getCanColaNutrients());
		return food;
	}
	
	public static Food getServingOmelette() {
		Food food = new Food("Omelette", 1.0, "Serving", Nutrients.getServingOmeletteNutrients());
		return food;
	}
	
	public static Food getServingChickenSoup() {
		Food food = new Food("Chicken Soup", 1.0, "Serving", Nutrients.getServingChickenSoupNutrients());
		return food;
	}
	
	public static Food getServingPestoPasta() {
		Food food = new Food("Pesto Pasta", 1.0, "Serving", Nutrients.getServingPestoPastaNutrients());
		return food;
	}
}