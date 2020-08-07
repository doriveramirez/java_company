package es.doriv.hibernate.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.doriv.hibernate.models.Supplier;

public class SupplierTest {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("Persistence");
		manager = emf.createEntityManager();
		Supplier supplier = new Supplier(10L, "David", "Spain");
		insert(supplier);
		print();
		update("France");
		print();
		delete(manager, supplier);
		print();
		manager.close();
	}

	private static void insert(Supplier supplier) {
		start();
		manager.persist(supplier);
		supplier.setCountry("Italy");
		end();
	}
	
	private static void update(String country) {
		start();
		Supplier supplier = manager.find(Supplier.class, 10L);
		supplier.setCountry(country);
		end();
	}
	
	private static void delete(EntityManager manager, Supplier supplier) {
		start();
		supplier = manager.merge(supplier);
		manager.remove(supplier);
		end();
	}

	@SuppressWarnings("unchecked")
	private static void print() {
		List<Supplier> suppliers = (List<Supplier>) manager.createQuery("FROM Supplier").getResultList();
		System.out.println("Number of suppliers: " + suppliers.size());
		for (Supplier supplier : suppliers) {
			System.out.println(supplier.toString());
		}
	}

	private static void end() {
		manager.getTransaction().commit();
	}

	private static void start() {
		manager.getTransaction().begin();
	}
	
}
