package com.uberaemos.ekmeksizdiettracker.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.core.model.DailyDiet;

public interface DailyDietRepository extends JpaRepository<DailyDiet, Long> {
	DailyDiet findByUserAndDate(User user, String date);
	Boolean existsByUserAndDate(User user, String date);
}
