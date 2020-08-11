package com.doriv.api_company.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doriv.api_company.controllers.UserController;
import com.doriv.api_company.models.Role;
import com.doriv.api_company.models.User;
import com.doriv.api_company.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user==null) throw new UsernameNotFoundException("User not found.");
		return new UserController(user);
	}

	public boolean isUserPresent(String username) {
		User user = repo.findByUsername(username);
		if (user!=null) {
			return true;
		}
		return false;
	}

	public void createUser(@Valid User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		user.setId();
		System.out.println("user_id" + user.getId());
		System.out.println("user_name" + user.getUsername());
		System.out.println("user_password" + user.getPassword());
		repo.save(user);
		System.out.println("encontrado" + repo.findByUsername(user.getUsername()).toString());
	}
	
}
