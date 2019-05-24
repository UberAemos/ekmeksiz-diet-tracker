package com.uberaemos.ekmeksizdiettracker.resource;

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

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.model.Food;
import com.uberaemos.ekmeksizdiettracker.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/users/{username}/{date}")
	public ResponseEntity<DailyDiet> getCourses(
			@PathVariable(value="username") String username,
			@PathVariable(value="date") String date) {
		
		DailyDiet diet = service.getDate(username, date);
		
		return new ResponseEntity<DailyDiet>(diet, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/{date}/{course}")
	public ResponseEntity<DailyDiet> addFood(
			@PathVariable(value="username") String username,
			@PathVariable(value="date") String dietDate,
			@PathVariable(value="course") String courseName,
			@RequestBody Food food) {
		
		DailyDiet newDailyDiet = service.addFood(username, dietDate, courseName, food);
		return new ResponseEntity<DailyDiet>(newDailyDiet, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{username}/{date}/{course}/{foodId}")
	public ResponseEntity<DailyDiet> deleteFood(
			@PathVariable(value="username") String username,
			@PathVariable(value="date") String dietDate,
			@PathVariable(value="course") String courseName,
			@PathVariable Long foodId) {
		
		DailyDiet newDailyDiet = service.deleteFood(username, dietDate, courseName, foodId);
		return new ResponseEntity<DailyDiet>(newDailyDiet, HttpStatus.OK);
	}
	
}
