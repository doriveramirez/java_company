package com.doriv.api_company.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
	@RequestMapping({ "/", "/home", "/index" })
	public String home() {
		return "views/home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "views/login";
	}
	
	@RequestMapping("/logout_success")
	public String logout() {
		return "views/logout";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		String string = "<a href='api/items'>View all Items</a>";
		string += "<br>";
		string += "<a href='api/items'>View all Suppliers</a>";
		return string;
	}
}
