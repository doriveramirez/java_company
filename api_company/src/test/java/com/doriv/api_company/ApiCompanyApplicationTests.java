package com.doriv.api_company;

//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.doriv.api_company.models.Item;
//import com.doriv.api_company.models.PriceReduction;
//import com.doriv.api_company.models.Supplier;

//@SpringBootTest
class ApiCompanyApplicationTests {
	
//	private static AnnotationConfigApplicationContext context;
//	private static EntityManager manager;
//	private static EntityManagerFactory emf;

//	private static Set<Item> items;
//	private static Set<Supplier> suppliers;
//	private static Set<PriceReduction> priceReductions;
//
//	private static Item item;
//	private static Supplier supplier;
//	private static PriceReduction priceReduction;
//	
//	@Test
//	void contextLoads() {
//		start();
//		addValues();
//		insert();
//		tables();
//		print("INSERT");
//		update();
//		print("UPDATE");
//		delete();
//		print("DELETE");
//		manager.close();
//	}
//	
//	private static void start() {
////		emf = Persistence.createEntityManagerFactory("Persistence");
////		manager = emf.createEntityManager();
//		context = new AnnotationConfigApplicationContext();
//		context.scan("com.doriv.api_company");
//		context.refresh();
//	}
//
//	@SuppressWarnings("unchecked")
//	private static void tables() {
//		suppliers = (HashSet<Supplier>) manager.createQuery("FROM Supplier").getResultList();
//		priceReductions = (HashSet<PriceReduction>) manager.createQuery("FROM PriceReduction").getResultList();
//		items = (HashSet<Item>) manager.createQuery("FROM Item").getResultList();
//	}
//
//	private static void addValues() {
//		item = new Item(111, 18, true, suppliers, priceReductions, LocalDate.now(), "admin");
//		supplier = new Supplier("David", "Spain", items);
//		priceReduction = new PriceReduction(12.5, LocalDate.now(), item);
//		item.addSupplier(supplier);
//		item.addPriceReduction(priceReduction);
//	}
//
//	private static void insert() {
//		begin();
//		manager.persist(item);
//		manager.persist(supplier);
//		manager.persist(priceReduction);
//		commit();
//	}
//
//	private static void update() {
//		begin();
//		// item = manager.find(Item.class, 10L);
//		item.setCreationDate(LocalDate.of(1997, 05, 27));
//		supplier.setName("test");
//		priceReduction.setReducedPrice(25.87);
//		commit();
//	}
//
//	private static void delete() {
//		begin();
//		// item = manager.merge(item);
//		manager.remove(item);
//		manager.remove(supplier);
//		manager.remove(priceReduction);
//		commit();
//	}
//
//	private static void print(String action) {
//		System.out.println("-------" + action + "-------");
//		System.out.println("Number of Items: " + items.size());
//		for (Item item : items) {
//			System.out.println(item.toString());
//		}
//		for (PriceReduction priceReduction : priceReductions) {
//			System.out.println(priceReduction.toString());
//		}
//		for (Supplier supplier : suppliers) {
//			System.out.println(supplier.toString());
//		}
//	}
//
//	private static void commit() {
//		manager.getTransaction().commit();
//	}
//
//	private static void begin() {
//		manager.getTransaction().begin();
//	}

}
