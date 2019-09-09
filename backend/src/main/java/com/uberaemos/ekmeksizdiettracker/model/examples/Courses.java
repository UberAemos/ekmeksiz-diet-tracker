package com.uberaemos.ekmeksizdiettracker.model.examples;

import com.uberaemos.ekmeksizdiettracker.model.Course;

public class Courses {
	
	public static Course getBreakfast() {
		Course course = new Course("Breakfast");
		course.addFood(Foods.getServingOmelette());
		course.addFood(Foods.getWholeRedApple());
		return course;
	}
	
	public static Course getLunch() {
		Course course = new Course("Lunch");
		course.addFood(Foods.getWholePizza());
		course.addFood(Foods.getCanCola());
		return course;
	}
	
	public static Course getDinner() {
		Course course = new Course("Dinner");
		course.addFood(Foods.getServingChickenSoup());
		course.addFood(Foods.getServingPestoPasta());
		return course;
	}
	
	public static Course getSnacks() {
		Course course = new Course("Snacks");
		course.addFood(Foods.getWholeRedApple());
		return course;
	}
}