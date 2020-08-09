package com.doriv.api_company.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.doriv.api_company.models.Testt;

public interface TestRepo extends CrudRepository<Testt, UUID> {

}
