package com.doriv.api_company.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doriv.api_company.models.Item;
import com.doriv.api_company.repo.ItemRepo;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepo repo;

	public List<Item> getAll() {
		List<Item> items = new ArrayList<>();
		repo.findAll().forEach(items::add);
		return items;
	}

	public Item get(UUID id) {
		return repo.findById(id).get();
	}

	public void add(Item item) {
		repo.save(item);
	}

	public void update(UUID id, Item item) {
		repo.save(item);
	}

	public void drop(UUID id) {
		repo.deleteById(id);
	}
	
	public void findByCode(int code) {
		repo.findByCode(code);
	}
	
	public List<Item> findByDescription(String description) {
		List<Item> items = new ArrayList<>();
		repo.findByDescription(description).forEach(items::add);
		return items;
	}
	
	public List<Item> findByState(boolean state) {
		List<Item> items = new ArrayList<>();
		repo.findByState(state).forEach(items::add);
		return items;
	}
	
	public List<Item> findByPrice(Double price) {
		List<Item> items = new ArrayList<>();
		repo.findByPrice(price).forEach(items::add);
		return items;
	}
	
	public List<Item> findByCreationDate(LocalDate creationDate) {
		List<Item> items = new ArrayList<>();
		repo.findByCreationDate(creationDate).forEach(items::add);
		return items;
	}
	
//	public List<ItemSupplier> findItemSupplier(UUID id) {
//		List<ItemSupplier> itemsSuppliers = new ArrayList<>();
//		mtmRepo.findByIdItem(id).forEach(itemsSuppliers::add);
//		return itemsSuppliers;
//	}
	
//	public List<Item> findBySuppliers(UUID idSupplier) {
//		List<Item> items = new ArrayList<>();
//		Supplier supplier = repoSupplier.findById(idSupplier).get();
//		repo.findBySuppliers(supplier).forEach(items::add);
//		return items;
//	}
	
//	public List<Item> findByPriceReductions(UUID idPriceReduction) {
//		List<Item> items = new ArrayList<>();
//		PriceReduction priceReduction = repoPriceReduction.findById(idPriceReduction).get();
//		repo.findByPriceReductions(priceReduction).forEach(items::add);
//		return items;
//	}
	
}