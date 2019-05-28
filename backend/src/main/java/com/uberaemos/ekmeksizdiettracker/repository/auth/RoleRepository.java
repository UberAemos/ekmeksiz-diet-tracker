package com.uberaemos.ekmeksizdiettracker.repository.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uberaemos.ekmeksizdiettracker.model.auth.Role;
import com.uberaemos.ekmeksizdiettracker.model.auth.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}