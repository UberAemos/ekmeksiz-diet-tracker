package com.uberaemos.ekmeksizdiettracker.core.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.auth.repository.UserRepository;
import com.uberaemos.ekmeksizdiettracker.core.model.Food;
import com.uberaemos.ekmeksizdiettracker.core.service.FoodService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class FoodController {
	
	@Autowired
	private FoodService service;

	@PostMapping("/foods")
	public ResponseEntity<List<Food>> searchFood(@RequestBody String foodName) {
		List<Food> foods = new ArrayList<>();
		foods.add(new Food("Musakka"));
		return new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
	}
	
	@PostMapping("/foods/{username}/{courseName}")
	public ResponseEntity<User> addFood(@PathVariable("courseName") String courseName, 
			@PathVariable("username") String username,
			@RequestBody Food food) {
		User user = service.addFood(courseName, username, food);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@GetMapping("/foods/{username}")
	public ResponseEntity<List<Food>> getFood(@PathVariable String username) {
		List<Food> foods = service.findByUsername(username);
		return new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
	}
}
