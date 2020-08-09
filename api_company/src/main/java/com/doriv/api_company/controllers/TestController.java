package com.doriv.api_company.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doriv.api_company.models.Testt;
import com.doriv.api_company.services.TestService;

@RestController
public class TestController {
	
	@Autowired
	private TestService service;
	
	@GetMapping("/tests")
	public List<Testt> getAllTests() {
		return service.getEveryTest();
	}
	
	@GetMapping("/tests/{id}")
	public Testt getTest(@PathVariable UUID id) {
		return service.getTest(id);
	}
	
	@PostMapping("/tests")
	public void postTest(@RequestBody Testt test) {
		service.addTest(test);
	}
	
	@PutMapping("/tests/{id}")
	public void putTest(@RequestBody Testt test, @PathVariable UUID id) {
		service.updateTest(id, test);
	}
	
	@DeleteMapping("/tests/{id}")
	public void deleteTest(@PathVariable UUID id) {
		service.dropTest(id);
	}
}
