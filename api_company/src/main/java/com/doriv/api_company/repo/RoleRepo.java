package com.doriv.api_company.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.doriv.api_company.models.Role;

public interface RoleRepo extends CrudRepository<Role, UUID>{
	Role findByName(String name);
}
