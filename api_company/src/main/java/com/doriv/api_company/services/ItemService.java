package com.doriv.api_company.services;

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
}