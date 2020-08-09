package com.doriv.api_company.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.doriv.api_company.models.PriceReduction;

public interface PriceReductionRepo extends CrudRepository<PriceReduction, UUID> {
	public List<PriceReduction> findByItemId(UUID id_item);
}
