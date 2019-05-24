package com.uberaemos.ekmeksizdiettracker.resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GuestController {

	@GetMapping("/guest/{date}")
	public DailyDiet getDefaultDiet(@PathVariable(value = "date") String dietDate) {
		return new DailyDiet(dietDate);
	}
}
