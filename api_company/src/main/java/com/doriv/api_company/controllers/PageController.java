package com.doriv.api_company.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
	@RequestMapping({ "/", "/home", "/index" })
	public String home() {
		return "views/home";
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
	
	@RequestMapping({ "api" })
	public String api() {
		return "views/api";
	}
	
	@RequestMapping({ "items" })
	public String items() {
		return "views/items";
	}
	
	@RequestMapping({ "item_{id}" })
	public String item(@PathVariable("id") String id) {
		return "views/item";
	}
	
	@RequestMapping({ "users" })
	public String users() {
		return "views/users";
	}
}
