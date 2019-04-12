package com.uberaemos.ekmeksizdiettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberaemos.ekmeksizdiettracker.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
