package com.doriv.api_company;

import com.doriv.api_company.controllers.TestController;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.doriv.api_company.models.Testt;
import com.doriv.api_company.services.TestService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestTests {

	private static TestController controller;

	private static Set<Testt> tests;

	private static Testt test;

	@Test
	@Rollback(false)
	public void testCreateTest() {
		controller.postTest(new Testt(1));
		System.out.println("hola " + controller.getAllTests());
	}
	
	private static void addValues() {
		test = new Testt();
		test.setName(123);
	}
	
	private static void insert() {
		System.out.print("hola " + test.toString());
	}

}
