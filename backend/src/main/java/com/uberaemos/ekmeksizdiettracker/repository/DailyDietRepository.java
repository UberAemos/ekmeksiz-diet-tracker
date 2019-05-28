package com.uberaemos.ekmeksizdiettracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

public interface DailyDietRepository extends JpaRepository<DailyDiet, Long> {
}
