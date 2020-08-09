package com.doriv.api_company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doriv.api_company.models.Supplier;
import com.doriv.api_company.repo.SupplierRepo;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepo repo;

	public List<Supplier> getAll() {
		List<Supplier> suppliers = new ArrayList<>();
		repo.findAll().forEach(suppliers::add);
		return suppliers;
	}

	public Supplier get(UUID id) {
		return repo.findById(id).get();
	}

	public void add(Supplier supplier) {
		repo.save(supplier);
	}

	public void update(UUID id, Supplier supplier) {
		repo.save(supplier);
	}

	public void drop(UUID id) {
		repo.deleteById(id);
	}
	
}
