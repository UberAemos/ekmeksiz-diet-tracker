package com.uberaemos.ekmeksizdiettracker.model.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.uberaemos.ekmeksizdiettracker.model.DailyDiet;

@Entity
public abstract class RegisteredUser extends User {
	/** The date when the user is registered */
	private Date registerDate;
	
	/** The date when the user has loginned last */
	private Date loginDate;
	
    // List of DailyDiet objects saved by the user
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	protected Map<String, DailyDiet> dietList = new HashMap<>();
	
	public RegisteredUser() {
		super();
	}
	
	public RegisteredUser(String username, String password, Role role) {
		super(username, password, role);
	}

	public RegisteredUser(String username, String password, Role role, DailyDiet diet) {
		super(username, password, role);
		diet.setInnerRelations();
		diet.setUser(this);
		dietList.put(diet.getDate(), diet);
		registerDate = new Date();
	}

	public Map<String, DailyDiet> getDietList() {return dietList;}
	public Date getRegisterDate() {return registerDate; }
	public Date getLoginDate() {return loginDate;}
	public void updateLoginDate() {loginDate = new Date(); }
	
	public abstract void addDiet(DailyDiet dailyDiet);
	
	public void deleteDiet(DailyDiet dailyDiet) {
		dailyDiet.setUser(null);
		dietList.remove(dailyDiet.getDate());
	}
	
	public Boolean containsDiet(String dietDate) {
		return dietList.containsKey(dietDate);
	}
	
	public DailyDiet getDiet(String dietDate) {
		if (containsDiet(dietDate)) return getExistingDailyDiet(dietDate);
		else return getNewDailyDiet(dietDate);
	}
	
	public DailyDiet getNewDailyDiet(String dietDate) {
		DailyDiet newDiet = new DailyDiet(dietDate);
		newDiet.setUser(this);
		return newDiet;
	}

	public DailyDiet getExistingDailyDiet(String dietDate) {
		return dietList.get(dietDate);
	}
}