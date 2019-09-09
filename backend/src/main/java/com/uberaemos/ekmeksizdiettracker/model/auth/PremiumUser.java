package com.uberaemos.ekmeksizdiettracker.model.auth;

import javax.persistence.Entity;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

@Entity
public class PremiumUser extends RegisteredUser {
	
	protected PremiumUser() {}
	
	public PremiumUser(String username, String password) {
		super(username, password, Role.ROLE_PM);
	}
	
	public PremiumUser(String username, String password, DailyDiet diet) {
		super(username, password, Role.ROLE_PM, diet);
	}

	@Override
	public void addDiet(DailyDiet dailyDiet) {
		dailyDiet.setUser(this);
		dietList.put(dailyDiet.getDate(), dailyDiet);
	}

}
