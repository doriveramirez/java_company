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

import com.doriv.api_company.models.Supplier;
import com.doriv.api_company.services.SupplierService;

@RestController
public class SupplierController {

	@Autowired
	private SupplierService service;
	
	@GetMapping("/suppliers")
	public List<Supplier> getAllSuppliers() {
		return service.getAll();
	}
	
	@GetMapping("/suppliers/{id}")
	public Supplier getSupplier(@PathVariable UUID id) {
		return service.get(id);
	}
	
	@PostMapping("/suppliers")
	public void postSupplier(@RequestBody Supplier supplier) {
		service.add(supplier);
	}
	
	@PutMapping("/suppliers/{id}")
	public void putSupplier(@RequestBody Supplier supplier, @PathVariable UUID id) {
		service.update(id, supplier);
	}
	
	@DeleteMapping("/suppliers/{id}")
	public void deleteSupplier(@PathVariable UUID id) {
		service.drop(id);
	}
	
}
