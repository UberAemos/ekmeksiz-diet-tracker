package com.uberaemos.ekmeksizdiettracker.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.core.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
