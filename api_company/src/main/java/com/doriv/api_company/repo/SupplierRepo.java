package com.doriv.api_company.repo;

import org.springframework.data.repository.CrudRepository;

import com.doriv.api_company.models.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Integer> {

}
