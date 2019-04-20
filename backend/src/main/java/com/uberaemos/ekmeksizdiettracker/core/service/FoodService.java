package com.uberaemos.ekmeksizdiettracker.core.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uberaemos.ekmeksizdiettracker.auth.model.User;
import com.uberaemos.ekmeksizdiettracker.auth.repository.UserRepository;
import com.uberaemos.ekmeksizdiettracker.core.model.Food;
import com.uberaemos.ekmeksizdiettracker.core.repository.FoodRepository;

@Repository
@Transactional(readOnly = true)
public class FoodService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Transactional
	public User addFood(String courseName, String username, Food food) {
		if (!userRepository.existsByUsername(username)) {
			userRepository.save(new User(username, "123456"));
		}
		User user = userRepository.findByUsername(username).get();
		System.out.println(user.getFoods().size());
		user.addFood(food);
		food.setUser(user);
		
		Food updatedFood = foodRepository.save(food);
		User updatedUser = userRepository.save(user);

		return updatedUser;
	}
	
	public List<Food> findByUsername(String username) {
		User user = userRepository.findByUsername(username).get();
		return user.getFoods();
	}
}
