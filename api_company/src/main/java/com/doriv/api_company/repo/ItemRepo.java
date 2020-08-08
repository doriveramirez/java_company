package com.doriv.api_company.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doriv.api_company.models.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
