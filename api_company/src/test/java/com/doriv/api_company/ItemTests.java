package com.doriv.api_company;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

import com.doriv.api_company.models.Item;
import com.doriv.api_company.repo.ItemRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ItemTests {

	@Autowired
	private static ItemRepo repo;
	
	private static Set<Item> items;
	
	private static Item item;
	
	@Test
	void testEverything() {
		addValues();
		insert();
		tables();
		print("INSERT");
		update();
		print("UPDATE");
//		delete();
//		print("DELETE");
	}
	
	@Test
	@Rollback(false)
	public void testCreateItem() {
		addValues();
		insert();
	}

	@SuppressWarnings("unchecked")
	private static void tables() {
		items = (HashSet<Item>) repo.findAll();
	}

	private static void addValues() {
		item = new Item(11, "Test", 43, true, null, null, LocalDate.now(), "admin");
		System.out.print("hola " + item.toString());
	}
	
	private static void insert() {
		Item savedItem = repo.save(item);
		assertNotNull(savedItem);
	}

	private static void update() {
		item.setDescription("hello");
	}

//	private static void delete() {
//		begin();
//		// item = manager.merge(item);
//		manager.remove(item);
//		manager.remove(supplier);
//		manager.remove(priceReduction);
//		commit();
//	}

	private static void print(String action) {
		System.out.println("-------" + action + "-------");
		System.out.println("Number of Items: " + items.size());
		for (Item item : items) {
			System.out.println(item.toString());
		}
	}
}
