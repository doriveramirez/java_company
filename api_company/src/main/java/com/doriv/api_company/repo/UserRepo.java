package com.doriv.api_company.repo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.doriv.api_company.models.User;

public interface UserRepo extends CrudRepository<User, UUID> {
	User findByUsername(String username);
}
