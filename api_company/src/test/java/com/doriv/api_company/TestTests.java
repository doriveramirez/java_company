package com.doriv.api_company;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.doriv.api_company.models.Item;
import com.doriv.api_company.models.Testt;
import com.doriv.api_company.repo.TestRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestTests {

	@Autowired
	private static TestRepo repo;

	private static Set<Testt> tests;

	private static Testt test;
	
	@Test
	@Rollback(false)
	public void testCreateTest() {
		addValues();
		insert();
	}
	
	private static void addValues() {
		test = new Testt();
		test.setName(123);
		System.out.print("hola " + test.toString());
	}
	
	private static void insert() {
		System.out.print("hola " + test.toString());
		Testt savedItem = repo.save(test);
		assertNotNull(savedItem);
	}

}
