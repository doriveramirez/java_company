package com.doriv.api_company.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doriv.api_company.models.Item;
import com.doriv.api_company.services.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {
	
	@Autowired
	private ItemService service;
	
	@GetMapping()
	public List<Item> getAllItems() {
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public Item getItem(@PathVariable UUID id) {
		return service.get(id);
	}
	
	@PostMapping()
	public void postItem(@RequestBody Item item) {
		service.add(item);
	}
	
	@PutMapping("/{id}")
	public void putItem(@RequestBody Item item, @PathVariable UUID id) {
		service.update(id, item);
	}
	
	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable UUID id) {
		service.drop(id);
	}
	
	@GetMapping("/description={description}")
	public List<Item> getAllItemsByDescription(@PathVariable String description) {
		return service.findByDescription(description);
	}
	
	@GetMapping("/state={state}")
	public List<Item> getAllItemsByState(@PathVariable boolean state) {
		return service.findByState(state);
	}
	
	@GetMapping("/price={price}")
	public List<Item> getAllItemsByPrice(@PathVariable Double price) {
		return service.findByPrice(price);
	}
	
	@GetMapping("/creationDate={creationDate}")
	public List<Item> getAllItemsByCreationDate(@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate creationDate) {
		return service.findByCreationDate(creationDate);
	}
	
//	@GetMapping("/itemSupplierId={id}")
//	public List<ItemSupplier> getItemSupplier(@PathVariable UUID id) {
//		return service.findItemSupplier(id);
//	}
	
//	@GetMapping("/supplierId={id}")
//	public List<Item> getAllItemsBySuppliers(@PathVariable UUID id) {
//		return service.findBySuppliers(id);
//	}
	
//	@GetMapping("/priceReductionId={id}")
//	public List<Item> getAllItemsByPriceReductions(@PathVariable UUID id) {
//		return service.findByPriceReductions(id);
//	}
}
