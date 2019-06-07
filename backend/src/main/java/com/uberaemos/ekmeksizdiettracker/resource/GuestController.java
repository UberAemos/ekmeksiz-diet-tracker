package com.uberaemos.ekmeksizdiettracker.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

/**
 * RESTController for unregistered guests
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GuestController {

	/**
	 * Produces a default DailyDiet template for the given date
	 * @param dietDate
	 * Date in YYMMDD format
	 * @return
	 * The created DailyDiet object
	 */
	@GetMapping("/guest/{date}")
	public ResponseEntity<DailyDiet> getDefaultDiet(@PathVariable(value = "date") String dietDate) {
		return new ResponseEntity<DailyDiet>(new DailyDiet(dietDate), HttpStatus.OK);
	}
}
