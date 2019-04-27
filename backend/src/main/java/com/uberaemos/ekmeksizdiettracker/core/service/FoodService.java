package com.uberaemos.ekmeksizdiettracker.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.core.model.Course;
import com.uberaemos.ekmeksizdiettracker.core.model.Food;
import com.uberaemos.ekmeksizdiettracker.core.repository.FoodRepository;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private CourseService courseService;
	
	public void saveFood(Food food, Course course) {
		food.setCourse(course);
		course.addFood(food);
		Food createdFood = foodRepository.save(food);
		System.out.println(createdFood.getId());
		courseService.save(course);
	}
	
	public Course delete(Food food) {
		Course course = food.getCourse();
		course.deleteFood(food);
		Course updatedCourse = courseService.save(course);
		foodRepository.deleteById(food.getId());
		return updatedCourse;
	}
}
