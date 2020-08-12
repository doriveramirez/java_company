package com.doriv.api_company.controllers;

import javax.swing.text.View;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.doriv.api_company.models.User;
import com.doriv.api_company.services.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService service;
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		boolean role = false;
		model.addAttribute("role", role);
		return "views/register";
	}
	
	@PostMapping("/register")
	public String registerAction(@Valid User user, BindingResult br, Model model) {
		if(br.hasErrors()) {
			return "views/register";
		}
		String role = "USER";
		String username = user.getUsername();
		if (username.contains("_admin")) {
			String[] strings = username.split("_");
			username = strings[0];
			user.setUsername(username);
			role = "ADMIN";
		}
		if(service.isUserPresent(username)) {
			model.addAttribute("exist", true);
			return "views/register";
		}
		service.createUser(user, role);
		return "views/home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "views/login";
	}
	
}
