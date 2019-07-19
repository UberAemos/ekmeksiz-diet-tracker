package com.uberaemos.ekmeksizdiettracker.model.auth;

/**
 * USER: Can create up to 10 DailyDiets
 * PM: Can create unlimited DailyDiets
 * ADMIN: Can create and delete the registered users
 */
public enum Role {
    ROLE_USER,
    ROLE_PM,
    ROLE_ADMIN
}
