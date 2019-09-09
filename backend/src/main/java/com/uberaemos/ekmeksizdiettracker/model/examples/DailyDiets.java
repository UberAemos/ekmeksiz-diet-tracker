package com.uberaemos.ekmeksizdiettracker.model.examples;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

public class DailyDiets {
	public static DailyDiet getDailyDiet() {
		DailyDiet dailyDiet = new DailyDiet(
				"010101", 
				Courses.getBreakfast(), 
				Courses.getLunch(),
				Courses.getDinner(),
				Courses.getSnacks());
		
		return dailyDiet;
	}
}