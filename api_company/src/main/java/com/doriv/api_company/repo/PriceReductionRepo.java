package com.doriv.api_company.repo;

import org.springframework.data.repository.CrudRepository;

import com.doriv.api_company.models.PriceReduction;

public interface PriceReductionRepo extends CrudRepository<PriceReduction, Integer> {

}
