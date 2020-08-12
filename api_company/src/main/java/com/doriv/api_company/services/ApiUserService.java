package com.doriv.api_company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doriv.api_company.models.User;
import com.doriv.api_company.repo.UserRepo;

@Service
public class ApiUserService {

	@Autowired
	private UserRepo repo;

	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		repo.findAll().forEach(users::add);
		return users;
	}

	public User get(UUID id) {
		return repo.findById(id).get();
	}

	public void add(User user) {
		repo.save(user);
	}

	public void update(UUID id, User user) {
		repo.save(user);
	}

	public void drop(UUID id) {
		repo.deleteById(id);
	}
	
}
