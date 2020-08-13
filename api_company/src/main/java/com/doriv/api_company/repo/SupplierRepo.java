package com.doriv.api_company.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.doriv.api_company.models.Item;
import com.doriv.api_company.models.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, UUID> {
	public List<Supplier> findByItems(Item item);
	public List<Supplier> findByName(String name);
}

