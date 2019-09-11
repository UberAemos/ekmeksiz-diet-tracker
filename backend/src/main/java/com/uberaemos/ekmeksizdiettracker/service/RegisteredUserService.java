package com.uberaemos.ekmeksizdiettracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.model.Course;
import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.model.Food;
import com.uberaemos.ekmeksizdiettracker.model.auth.RegisteredUser;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;
import com.uberaemos.ekmeksizdiettracker.repository.CourseRepository;
import com.uberaemos.ekmeksizdiettracker.repository.FoodRepository;
import com.uberaemos.ekmeksizdiettracker.repository.auth.UserRepository;

@Service
public class RegisteredUserService {
	
	@Autowired private UserRepository userRepository;
	@Autowired private CourseRepository courseRepository;
	@Autowired private FoodRepository foodRepository;
	
	public DailyDiet addFood(
			long courseId,
			Food food) {
		Course course = courseRepository.findById(courseId).get();
		course.addFood(food);
		Food newFood = foodRepository.save(food);
		return (DailyDiet) newFood.highestContainer();
	}
	
	public DailyDiet deleteFood(
			Long foodId) {
		Food food = foodRepository.findById(foodId).get();
		Course course = food.getCourse();
		course.deleteFood(food);
		Course newCourse = courseRepository.save(course);
		return (DailyDiet) newCourse.highestContainer();
	}
	
	public DailyDiet incrementFood(Long foodId) {
		Food food = foodRepository.findById(foodId).get();
		food.addQuantity();
		Food newFood = foodRepository.save(food);
		return (DailyDiet) newFood.highestContainer();
	}
	
	public DailyDiet subtractFood(Long foodId) {
		Food food = foodRepository.findById(foodId).get();
		if (food.getQuantity() == 1) {
			return deleteFood(foodId);
		} else {
			food.subtractQuantity();
			Food newFood = foodRepository.save(food);
			return (DailyDiet) newFood.highestContainer();
		}
	}
	
	public User updateUserLoginDate(RegisteredUser user) {
		user.updateLoginDate();
		return userRepository.save(user);
	}
	
	public DailyDiet getDailyDiet(String username, String dailyDietDate) {
		DailyDiet dailyDiet;
		RegisteredUser user = (RegisteredUser) userRepository.findByUsername(username);
		if (user.containsDiet(dailyDietDate)) {
			dailyDiet = user.getDiet(dailyDietDate);
		} else {
			dailyDiet = user.getNewDailyDiet(dailyDietDate);
		}
		return dailyDiet;
	}
}