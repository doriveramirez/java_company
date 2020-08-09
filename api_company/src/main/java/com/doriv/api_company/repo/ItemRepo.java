package com.doriv.api_company.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.doriv.api_company.models.Item;
import com.doriv.api_company.models.PriceReduction;
import com.doriv.api_company.models.Supplier;

public interface ItemRepo extends CrudRepository<Item, UUID> {
	public List<Item> findByCode(int code);
	public List<Item> findByDescription(String description);
	public List<Item> findByState(boolean state);
	public List<Item> findByPrice(double price);
	public List<Item> findByCreationDate(LocalDate creationDate);
	public List<Item> findBySuppliers(Supplier supplier);
	public List<Item> findByPriceReductions(PriceReduction priceReduction);
}
