package com.uberaemos.ekmeksizdiettracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.model.Course;
import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.model.Food;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;
import com.uberaemos.ekmeksizdiettracker.repository.FoodRepository;
import com.uberaemos.ekmeksizdiettracker.repository.auth.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	public DailyDiet getDate(String username, String dietName) {
		User user = userRepository.findByUsername(username).get();
		return user.getDailyDiet(dietName);
	}
	
	public DailyDiet addFood(String username, 
			String dietDate, 
			String courseName,
			Food food) {
		
		User user = userRepository.findByUsername(username).get();
		DailyDiet diet = user.getDailyDiet(dietDate);
		Course course = diet.getCourse(courseName);
		course.addFood(food);
		diet.calculateTotal();
		if (!user.containsDiet(dietDate)) {
			user.addDietList(diet);
		}
		
		user = userRepository.save(user);
		return user.getDailyDiet(dietDate);
	}
	
	public DailyDiet deleteFood(String username, 
			String dietDate, 
			String courseName,
			Long foodId) {
		
		Food deleteFood = foodRepository.findById(foodId).get();
		User user = userRepository.findByUsername(username).get();
		DailyDiet diet = user.getDailyDiet(dietDate);
		Course course = diet.getCourse(courseName);
		course.deleteFood(deleteFood);
		diet.calculateTotal();
		User newUser = userRepository.save(user);
		return newUser.getDailyDiet(dietDate);
	}
}
