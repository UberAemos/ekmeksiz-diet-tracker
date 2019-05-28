package com.uberaemos.ekmeksizdiettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {	
}
