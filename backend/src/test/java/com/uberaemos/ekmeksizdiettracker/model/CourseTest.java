package com.uberaemos.ekmeksizdiettracker.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.uberaemos.ekmeksizdiettracker.model.examples.Courses;
import com.uberaemos.ekmeksizdiettracker.model.examples.Foods;

public class CourseTest {
	Course exampleCourse;
	Course breakfast;
	Food servingOmelette;
	
	@BeforeEach
	public void init() {
		exampleCourse = new Course("example");
		breakfast = Courses.getBreakfast();
		servingOmelette = Foods.getServingOmelette();
	}
	
	@Test
	public void whenEmptyCourseIsCreated_nutritionValuesAreZero() {
		for (Nutrient n : Nutrient.values()) {
			assertTrue(exampleCourse.getNutrients().get(n).equals(0.0),
					"Nutrient value for empty course should be 0.0");
		}
	}
	
	@Test
	public void whenFoodAddedToEmptyCourse_nutritionValuesAreZero() {
		exampleCourse.addNutritious(servingOmelette);
		assertTrue(servingOmelette.getUpperContainer() == exampleCourse,
				"Omelette's container should be exampleCourse");
		
		for (Nutrient n : Nutrient.values()) {
			assertTrue(exampleCourse.getNutrients().get(n).equals(
					Foods.getServingOmelette().getNutrients().get(n)),
					"Nutrient value should be the same as the added food");
		}
	}
	
	@Test
	public void whenFoodAddedToBreakfast_foodNutritionIsAddedToBreakfast() {
		breakfast.addNutritious(servingOmelette);
		for (Nutrient n : Nutrient.values()) {
			assertTrue(breakfast.getNutrients().get(n).equals(
					Math.round(
						(Foods.getServingOmelette().getNutrients().get(n) +
						Foods.getServingOmelette().getNutrients().get(n) +
						Foods.getWholeRedApple().getNutrients().get(n)) * 100.0) / 100.0),
					"Breakfast nutrition should be the sum of all contained foods");
		}
	}
	
	@Test
	public void whenFoodDeletedFromBreakfast_foodNutritionIsSubtractedFromBreakfast() {
		breakfast.deleteNutritious(servingOmelette);
		assertTrue(servingOmelette.getUpperContainer() == null,
				"Serving Omelette's conteiner should be null");
		for (Nutrient n : Nutrient.values()) {
			assertTrue(breakfast.getNutrients().get(n).equals(
				Math.round(Foods.getWholeRedApple().getNutrients().get(n) * 100.0) / 100.0),
					"Breakfast nutrition should be the sum of all contained foods");
		}
	}
}