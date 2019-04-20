package com.uberaemos.ekmeksizdiettracker.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.core.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
	
}
