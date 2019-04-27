package com.uberaemos.ekmeksizdiettracker.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.core.model.Course;
import com.uberaemos.ekmeksizdiettracker.core.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.core.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private DailyDietService dailyDietService;
	
	
	public Course save(Course course) {
		DailyDiet dailyDiet = course.getDailyDiet();
		dailyDiet.setCourse(course);
		Course updatedCourse = courseRepository.save(course);
		dailyDietService.save(dailyDiet);
		return updatedCourse;
	}
}
