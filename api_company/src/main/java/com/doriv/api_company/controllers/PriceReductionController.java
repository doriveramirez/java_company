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

import com.doriv.api_company.models.PriceReduction;
import com.doriv.api_company.services.PriceReductionService;

@RestController
public class PriceReductionController {

	@Autowired
	private PriceReductionService service;
	
	@GetMapping("/items/{id_item}/priceReductions")
	public List<PriceReduction> getAllPriceReductions(@PathVariable UUID id_item) {
		return service.getAll(id_item);
	}
	
	@GetMapping("/items/{id_item}/priceReductions/{id}")
	public PriceReduction getPriceReduction(@PathVariable UUID id) {
		return service.get(id);
	}
	
	@PostMapping("/items/{id_item}/priceReductions")
	public void postPriceReduction(@RequestBody PriceReduction priceReduction) {
		service.add(priceReduction);
	}
	
	@PutMapping("/items/{id_item}/priceReductions/{id}")
	public void putPriceReduction(@RequestBody PriceReduction priceReduction, @PathVariable UUID id) {
		service.update(id, priceReduction);
	}
	
	@DeleteMapping("/items/{id_item}/priceReductions/{id}")
	public void deletePriceReduction(@PathVariable UUID id) {
		service.drop(id);
	}
	
}
