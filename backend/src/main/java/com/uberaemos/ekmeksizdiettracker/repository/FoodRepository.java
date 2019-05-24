package com.uberaemos.ekmeksizdiettracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.model.Food;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
