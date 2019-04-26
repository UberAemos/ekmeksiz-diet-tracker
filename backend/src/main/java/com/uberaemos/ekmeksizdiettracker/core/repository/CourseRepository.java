package com.uberaemos.ekmeksizdiettracker.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.core.model.Course;
import com.uberaemos.ekmeksizdiettracker.core.model.DailyDiet;

public interface CourseRepository extends JpaRepository<Course, Long> {
	Course findByUserAndDailyDietAndName(User user, DailyDiet dailyDiet, String name);	
}
