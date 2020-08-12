package com.doriv.api_company.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doriv.api_company.models.User;
import com.doriv.api_company.services.ApiUserService;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

	@Autowired
	private ApiUserService service;

	@GetMapping()
	public List<User> getAllUsers() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable UUID id) {
		return service.get(id);
	}

	@PostMapping()
	public void postUser(@RequestBody User user) {
		service.add(user);
	}

	@PutMapping("/{id}")
	public void putUser(@RequestBody User user, @PathVariable UUID id) {
		service.update(id, user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable UUID id) {
		service.drop(id);
	}

}
