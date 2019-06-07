package com.uberaemos.ekmeksizdiettracker.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

/**
 * RestController for registered app users
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	/**
	 * Finds the given DailyDiet of the given User
	 * @param username
	 * Username for the registered user
	 * @param date
	 * Date in YYMMDD format for DailyDiet 
	 * @return
	 * The DailyDiet object
	 */
	@PreAuthorize("hasRole('USER') or hasRole('PM')")
	@GetMapping("/users/{username}/{date}")
	public ResponseEntity<DailyDiet> getDate(
			@PathVariable(value="username") String username,
			@PathVariable(value="date") String date) {
		
		DailyDiet diet = service.getDate(username, date);
		
		return new ResponseEntity<DailyDiet>(diet, HttpStatus.OK);
	}
	
	/**
	 * Adds the given food to the related course of the user
	 * @param username
	 * Username of registered user
	 * @param dietDate
	 * Date in YYMMDD format for the DailyDiet
	 * @param courseName
	 * Name of the course to save the food
	 * @param food
	 * Food object to add
	 * @return
	 * New DailyDiet object with the saved food object
	 */
	@PreAuthorize("hasRole('USER') or hasRole('PM')")
	@PostMapping("/users/{username}/{date}/{course}")
	public ResponseEntity<DailyDiet> addFood(
			@PathVariable(value="username") String username,
			@PathVariable(value="date") String dietDate,
			@PathVariable(value="course") String courseName,
			@RequestBody Food food) {
		
		DailyDiet newDailyDiet = service.addFood(username, dietDate, courseName, food);
		return new ResponseEntity<DailyDiet>(newDailyDiet, HttpStatus.OK);
	}
	
	/**
	 * Deletes the food with given id from the related course of the user
	 * @param username
	 * Username of the registered user
	 * @param dietDate
	 * Date in YYMMDD format for the DailyDiet
	 * @param courseName
	 * Name of the course to delete the food from
	 * @param foodId
	 * Identification number of the Food in FoodRepository
	 * @return
	 * New DailyDiet object without the deleted food object
	 */
	@PreAuthorize("hasRole('USER') or hasRole('PM')")
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
