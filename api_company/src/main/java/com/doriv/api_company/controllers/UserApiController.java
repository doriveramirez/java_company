package com.doriv.api_company.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doriv.api_company.models.Role;
import com.doriv.api_company.models.User;
import com.doriv.api_company.services.UserApiService;
import com.doriv.api_company.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

	@Autowired
	private UserApiService service;
	
	@Autowired
	private UserService userService;
	
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
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		String username = user.getUsername();
		String roleString = "USER";
		if (username.contains("_admin")) {
			System.out.println("pasa admin");
			String[] strings = username.split("_");
			username = strings[0];
			user.setUsername(username);
			roleString = "ADMIN";
		}
		System.out.println("Hola2" + roleString);
		Role role = userService.getRole(roleString);
		System.out.println("Hola3" + role);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		System.out.println("Hola" + roles);
		user.setRoles(roles);
		service.add(user);
	}
	
	@PutMapping("/{id}")
	public void putUser(@RequestBody User user, @PathVariable UUID id) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		String username = user.getUsername();
		String roleString = "USER";
		if (username.contains("_admin")) {
			String[] strings = username.split("_");
			username = strings[0];
			user.setUsername(username);
			roleString = "ADMIN";
		}
		Role role = userService.getRole(roleString);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		service.update(id, user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable UUID id) {
		User user = service.get(id);
		user.setRoles(null);
		service.drop(id);
	}
	
}
