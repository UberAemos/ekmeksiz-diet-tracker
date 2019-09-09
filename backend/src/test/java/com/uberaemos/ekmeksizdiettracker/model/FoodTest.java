package com.uberaemos.ekmeksizdiettracker.model;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.uberaemos.ekmeksizdiettracker.model.examples.Foods;

public class FoodTest {
	Food wholeRedApple;
	Food wholePizza;
	
	@BeforeEach
	public void init() {
		wholeRedApple = Foods.getWholeRedApple();
		wholePizza = Foods.getWholePizza();
	}
	@Test
	public void whenMultipleQuantityFoodIsCreated_unitNutrientsAreCalculated() {
		Map<Nutrient, Double> unitNutrients = wholeRedApple.getUnitNutrients();
		assertAll(
			() -> assertTrue(unitNutrients.get(Nutrient.ENERC_KCAL).equals(99.64),
				"Unit energy should be 99.64"),
			() -> assertTrue(unitNutrients.get(Nutrient.CHOCDF).equals(125.67),
				"Unit carbs should be 125.671"),
			() -> assertTrue(unitNutrients.get(Nutrient.FAT).equals(0.30),
				"Unit fat should be 0.3"),
			() -> assertTrue(unitNutrients.get(Nutrient.PROCNT).equals(0.47),
				"Unit protein should be 0.47"),
			() -> assertTrue(unitNutrients.get(Nutrient.SUGAR).equals(18.9),
				"Unit sugar should be 18.9")
		);
	}
	
	@Test
	public void whenAddQuantity_foodQuantityIsIncreased() {
		wholePizza.addQuantity();
		Map<Nutrient, Double> nutrients = wholePizza.getNutrients();
		Map<Nutrient, Double> unitNutrients = wholePizza.getUnitNutrients();
		assertTrue(wholePizza.getQuantity() == 2.0, "Food quantity should be 2");
		for (Nutrient n : Nutrient.values()) {
			assertTrue(nutrients.get(n) == unitNutrients.get(n) * 2,
					"Total nutrient should double unit nutrient");
		}
	}
	
	@Test
	public void whenSubtractQuantity_foodQuantityIsDecreased() {
		wholeRedApple.subtractQuantity();
		Map<Nutrient, Double> nutrients = wholeRedApple.getNutrients();
		Map<Nutrient, Double> unitNutrients = wholeRedApple.getUnitNutrients();
		assertTrue(wholeRedApple.getQuantity() == 1.0, "Food quantity should be 1");
		for (Nutrient n : Nutrient.values()) {
			assertTrue(nutrients.get(n).equals(unitNutrients.get(n)),
				"Total nutrient should be equal to unit nutrient");
		}
	}
	
	@Test
	public void whenSubtractQuantityOfUnitFood_foodIsDeleted() {
		Course course = new Course("Breakfast");
		course.addNutritious(wholePizza);
		wholePizza.subtractQuantity();
		
		assertTrue(course.getNutritiousList().size() == 0, 
			"Pizza should be deleted from the food list after being reduced to 0 quantity");
	}
}