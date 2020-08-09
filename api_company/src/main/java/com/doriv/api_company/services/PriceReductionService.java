package com.doriv.api_company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doriv.api_company.models.PriceReduction;
import com.doriv.api_company.repo.PriceReductionRepo;

@Service
public class PriceReductionService {
	
	@Autowired
	private PriceReductionRepo repo;

	public List<PriceReduction> getAll(UUID id_item) {
		List<PriceReduction> priceReductions = new ArrayList<>();
		repo.findByItemId(id_item).forEach(priceReductions::add);
		return priceReductions;
	}

	public PriceReduction get(UUID id) {
		return repo.findById(id).get();
	}

	public void add(PriceReduction priceReduction) {
		System.out.println("holarepo " + priceReduction);
		repo.save(priceReduction);
	}

	public void update(UUID id, PriceReduction priceReduction) {
		repo.save(priceReduction);
	}

	public void drop(UUID id) {
		repo.deleteById(id);
	}
}
