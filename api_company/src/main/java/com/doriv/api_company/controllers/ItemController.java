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

import com.doriv.api_company.models.Item;
import com.doriv.api_company.services.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService service;
	
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return service.getAll();
	}
	
	@GetMapping("/items/{id}")
	public Item getItem(@PathVariable UUID id) {
		return service.get(id);
	}
	
	@PostMapping("/items")
	public void postItem(@RequestBody Item item) {
		service.add(item);
	}
	
	@PutMapping("/items/{id}")
	public void putItem(@RequestBody Item item, @PathVariable UUID id) {
		service.update(id, item);
	}
	
	@DeleteMapping("/items/{id}")
	public void deleteItem(@PathVariable UUID id) {
		service.drop(id);
	}
}
