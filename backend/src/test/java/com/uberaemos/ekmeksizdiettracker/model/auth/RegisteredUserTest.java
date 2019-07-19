package com.uberaemos.ekmeksizdiettracker.model.auth;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.uberaemos.ekmeksizdiettracker.exception.PremiumAccountRequirementException;
import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

public class RegisteredUserTest {

	FreeUser freeUser;
	PremiumUser premiumUser;
	
	DailyDiet testDiet = new DailyDiet("test");
	
	@BeforeEach
	public void setup() {
		freeUser = new FreeUser("FreeUser", "password");
		premiumUser = new PremiumUser("PremiumUser", "password");
	}

	@Test
	public void whenAddDiet_dietListContainsDiet() {
		freeUser.addDiet(testDiet);
		premiumUser.addDiet(testDiet);
		
		assertAll(
			() -> assertTrue(freeUser.containsDiet(testDiet.getDate()), 
					"FreeUser should contain testDiet"),
			() -> assertTrue(freeUser.getDiet(testDiet.getDate()).getDate().equals(testDiet.getDate()), 
					"FreeUser's testDiet and testDiet should have the same date"),
			() -> assertTrue(premiumUser.containsDiet(testDiet.getDate()), 
					"premiumUser should contain testDiet"),
			() -> assertTrue(premiumUser.getDiet(testDiet.getDate()).getDate().equals(testDiet.getDate()), 
					"premiumUser's testDiet and testDiet should have the same date")
		);		
	}

	@Test
	public void whenAddDiet_toPremiumUserWithFullQuota_dietIsAdded() {
		for (int i = 0; i < freeUser.getDietQuota() + 1; i++) {
			premiumUser.addDiet(new DailyDiet(Integer.toString(i)));
		}
		assertTrue(premiumUser.getDietList().size() == freeUser.getDietQuota() + 1,  
				"premiumUser should not have a diet quota");
	}
	
	@Test
	public void whenAddDiet_toFreeUserWithFullQuota_exceptionThrown() {
		for (int i = 0; i < freeUser.getDietQuota(); i++) {
			freeUser.addDiet(new DailyDiet(Integer.toString(i)));
		}
		assertThrows(PremiumAccountRequirementException.class, () -> freeUser.addDiet(testDiet), 
				"freeUser should be in diet limit and should throw exception");
	}
	
	@Test
	public void whenAddDiet_dietUserIsChanged() {
		freeUser.addDiet(testDiet);
		assertTrue(freeUser.getDiet(testDiet.getDate()).getUser().equals(freeUser), 
				"Added testUser should have freeUser as its user");
	}
}
