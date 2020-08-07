package es.doriv.hibernate.tests;

import java.util.List;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.doriv.hibernate.models.PriceReduction;

public class PriceReductionTest {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("Persistence");
		manager = emf.createEntityManager();
		PriceReduction priceReduction = new PriceReduction(10L, 25, LocalDate.now());
		insert(priceReduction);
		print();
		update("France");
		print();
		delete(manager, priceReduction);
		print();
		manager.close();
	}

	private static void insert(PriceReduction priceReduction) {
		start();
		manager.persist(priceReduction);
		priceReduction.setReducedPrice(22.5);;
		end();
	}
	
	private static void update(String country) {
		start();
		PriceReduction priceReduction = manager.find(PriceReduction.class, 10L);
		priceReduction.setStartDate(LocalDate.of(1997, 05, 27));
		end();
	}
	
	private static void delete(EntityManager manager, PriceReduction priceReduction) {
		start();
		priceReduction = manager.merge(priceReduction);
		manager.remove(priceReduction);
		end();
	}

	@SuppressWarnings("unchecked")
	private static void print() {
		List<PriceReduction> priceReductions = (List<PriceReduction>) manager.createQuery("FROM PriceReduction").getResultList();
		System.out.println("Number of Price Reductions: " + priceReductions.size());
		for (PriceReduction priceReduction : priceReductions) {
			System.out.println(priceReduction.toString());
		}
	}

	private static void end() {
		manager.getTransaction().commit();
	}

	private static void start() {
		manager.getTransaction().begin();
	}

}
