package com.uberaemos.ekmeksizdiettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.model.Course;
import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;

public interface CourseRepository extends JpaRepository<Course, Long> {	
}
