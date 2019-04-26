package com.uberaemos.ekmeksizdiettracker.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bouncycastle.crypto.tls.DigitallySigned;
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


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class FoodController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DailyDietRepository dailyDietRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	@GetMapping("/users/{username}/{date}")
	public ResponseEntity<List<Course>> getCourses(@PathVariable(value="username") String username,
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
	public ResponseEntity<Food> addFood(@PathVariable(value="username") String username,
			@PathVariable(value="date") String date,
			@PathVariable(value="course") String courseName,
			@RequestBody Food food) {
		if (!userRepository.existsByUsername(username)) {
			userRepository.save(new User(username, "123456"));
		}
		User user = userRepository.findByUsername(username);
		if (!dailyDietRepository.existsByUserAndDate(user, date)) {
			Course breakfast = new Course("breakfast");
			Course lunch = new Course("lunch");
			Course dinner = new Course("dinner");
			Course snacks = new Course("snacks");
			
			DailyDiet dailyDiet = new DailyDiet(date);
			dailyDiet.setUser(user);

			dailyDiet.addCourses(breakfast);
			dailyDiet.addCourses(lunch);
			dailyDiet.addCourses(dinner);
			dailyDiet.addCourses(snacks);
			
			for (int i = 0; i < dailyDiet.getCourses().size(); i++) {
				Course course = dailyDiet.getCourses().get(i);
				course.setUser(user);
				course.setDailyDiet(dailyDiet);
				
			}
			dailyDietRepository.save(dailyDiet);
			for (int i = 0; i < dailyDiet.getCourses().size(); i++) {
				courseRepository.save(dailyDiet.getCourses().get(i));
			}
		}
		DailyDiet dailyDiet = dailyDietRepository.findByUserAndDate(user, date);
		
		Course course = courseRepository.findByUserAndDailyDietAndName(user, dailyDiet, courseName);
		course.addFood(food);
		dailyDiet.setCourse(course);
		user.setDailyDiet(dailyDiet);
		
		food.setCourse(course);
		food.setDailyDiet(dailyDiet);
		food.setUser(user);
		
		userRepository.save(user);
		dailyDietRepository.save(dailyDiet);
		courseRepository.save(course);
		foodRepository.save(food);
		
		return new ResponseEntity<Food>(food, HttpStatus.OK);
	}
	/*
	@DeleteMapping("/users/{username}/{date}/{course}")
	public ResponseEntity<Food> addFood(@PathVariable(value="username") String username,
			@PathVariable(value="date") String date,
			@PathVariable(value="course") String courseName,
			@RequestBody String foodName) {
	}
	*/
}
