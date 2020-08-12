package com.doriv.api_company.services;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.doriv.api_company.repo.RoleRepo;
import com.doriv.api_company.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private RoleRepo roleRepo;
	
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

	public void createUser(@Valid User user, String roleName) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role roleAux = roleRepo.findByName(roleName);
		Role role = new Role(roleName);
		if (roleAux == null) {
			roleRepo.save(role);
		} else {
			role = roleAux;
		}
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		user.setId();
		repo.save(user);
	}
	
}
