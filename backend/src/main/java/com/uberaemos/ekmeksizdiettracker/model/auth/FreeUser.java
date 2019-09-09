package com.uberaemos.ekmeksizdiettracker.model.auth;

import javax.persistence.Entity;

import com.uberaemos.ekmeksizdiettracker.exception.PremiumAccountRequirementException;
import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

@Entity
public class FreeUser extends RegisteredUser {

	protected FreeUser() {}
	
	/** Maximum number of DailyDiets an unpaid user can have */
	private static int DIET_QUOTA = 10;
	
	public FreeUser(String username, String password) {
		super(username, password, Role.ROLE_USER);
	}
	
	public FreeUser(String username, String password, DailyDiet diet) {
		super(username, password, Role.ROLE_USER, diet);
	}
	
	public int getDietQuota() {return DIET_QUOTA;}

	@Override
	public void addDiet(DailyDiet dailyDiet) {
		if (dietList.size() < DIET_QUOTA) {
			dailyDiet.setUser(this);
			dietList.put(dailyDiet.getDate(), dailyDiet);
		} else {
			throw new PremiumAccountRequirementException("Number of diets cannot go beyond " + DIET_QUOTA);
		}	
	}
}
