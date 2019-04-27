package com.uberaemos.ekmeksizdiettracker.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.auth.service.UserService;
import com.uberaemos.ekmeksizdiettracker.core.model.Course;
import com.uberaemos.ekmeksizdiettracker.core.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.core.repository.CourseRepository;
import com.uberaemos.ekmeksizdiettracker.core.repository.DailyDietRepository;

@Service
public class DailyDietService {
	
	@Autowired
	private DailyDietRepository dailyDietRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserService userService;

	public DailyDiet initialize(User user, String date) {
		DailyDiet dailyDiet = new DailyDiet(date);
		dailyDiet.setUser(user);
		
		for (int i = 0; i < dailyDiet.getCourses().size(); i++) {
			Course course = dailyDiet.getCourses().get(i);
			course.setUser(user);
			course.setDailyDiet(dailyDiet);
		}
		
		dailyDietRepository.save(dailyDiet);
		for (int i = 0; i < dailyDiet.getCourses().size(); i++) {
			courseRepository.save(dailyDiet.getCourses().get(i));
		}
		return dailyDiet;
	}
	
	public void save(DailyDiet dailyDiet) {
		User user = dailyDiet.getUser();
		user.setDailyDiet(dailyDiet);
		dailyDietRepository.save(dailyDiet);
		userService.save(user);
	}
}
