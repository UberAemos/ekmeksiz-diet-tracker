package com.uberaemos.ekmeksizdiettracker.core.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.auth.repository.UserRepository;
import com.uberaemos.ekmeksizdiettracker.core.model.Course;
import com.uberaemos.ekmeksizdiettracker.core.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.core.model.Food;
import com.uberaemos.ekmeksizdiettracker.core.repository.CourseRepository;
import com.uberaemos.ekmeksizdiettracker.core.repository.DailyDietRepository;
import com.uberaemos.ekmeksizdiettracker.core.repository.FoodRepository;
import com.uberaemos.ekmeksizdiettracker.core.service.DailyDietService;
import com.uberaemos.ekmeksizdiettracker.core.service.FoodService;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class FoodController {
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private DailyDietService dailyDietService;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DailyDietRepository dailyDietRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	@GetMapping("/users/{username}/{date}")
	public ResponseEntity<List<Course>> getCourses(
			@PathVariable(value="username") String username,
			@PathVariable(value="date") String date) {
		
		List<Course> courseList = new ArrayList<>();
		
		if(userRepository.existsByUsername(username)) {
			User user = userRepository.findByUsername(username);
			if (dailyDietRepository.existsByUserAndDate(user, date)) {
				DailyDiet dailyDiet = dailyDietRepository.findByUserAndDate(user, date);
				courseList = dailyDiet.getCourses();
			}
		}
		return new ResponseEntity<List<Course>>(courseList, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/{date}/{course}")
	public ResponseEntity<Food> addFood(
			@PathVariable(value="username") String username,
			@PathVariable(value="date") String date,
			@PathVariable(value="course") String courseName,
			@RequestBody Food food) {
		
		if (!userRepository.existsByUsername(username)) {
			userRepository.save(new User(username, "123456"));
		}
		User user = userRepository.findByUsername(username);
		if (!dailyDietRepository.existsByUserAndDate(user, date)) {
			dailyDietService.initialize(user, date);
		}
		DailyDiet dailyDiet = dailyDietRepository.findByUserAndDate(user, date);
		
		Course course = courseRepository.findByUserAndDailyDietAndName(user, dailyDiet, courseName);
		foodService.saveFood(food, course);
		return new ResponseEntity<Food>(food, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{username}/{date}/{course}/{id}")
	public ResponseEntity<Course> deleteFood(
			@PathVariable(value="username") String username,
			@PathVariable(value="date") String date,
			@PathVariable(value="course") String courseName,
			@PathVariable(value="id") Long id) {
		
			Food food = foodRepository.findById(id).get();
			Course course = food.getCourse();
			Course updatedCourse = foodService.delete(food);
			return new ResponseEntity<Course>(updatedCourse, HttpStatus.OK);
	}
	
}
