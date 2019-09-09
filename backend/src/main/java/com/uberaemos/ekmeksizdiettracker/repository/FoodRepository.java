package com.uberaemos.ekmeksizdiettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
