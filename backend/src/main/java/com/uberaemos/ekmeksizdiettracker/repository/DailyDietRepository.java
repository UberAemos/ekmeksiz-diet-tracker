package com.uberaemos.ekmeksizdiettracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;
import com.uberaemos.ekmeksizdiettracker.model.auth.User;

public interface DailyDietRepository extends JpaRepository<DailyDiet, Long> {
}
