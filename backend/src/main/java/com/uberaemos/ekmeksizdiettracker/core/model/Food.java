package com.uberaemos.ekmeksizdiettracker.core.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uberaemos.ekmeksizdiettracker.auth.model.User;

@Entity
public class Food {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	protected Food() {
		
	}
	
	public Food(String name) {
		super();
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + "]";
	}
}
