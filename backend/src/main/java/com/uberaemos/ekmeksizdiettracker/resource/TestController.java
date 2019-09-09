package com.uberaemos.ekmeksizdiettracker.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uberaemos.ekmeksizdiettracker.model.Course;
import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.model.Food;
import com.uberaemos.ekmeksizdiettracker.model.examples.Courses;
import com.uberaemos.ekmeksizdiettracker.model.examples.Foods;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TestController {

	@GetMapping("/test/diet")
	public ResponseEntity<DailyDiet> getDefaultFood() {
		return new ResponseEntity<DailyDiet>(new DailyDiet("000000"), HttpStatus.OK);
	}
}
