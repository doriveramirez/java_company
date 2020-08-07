package es.doriv.hibernate.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	private int code;
	private double Price;
	private boolean State;
	private List<Supplier> suppliers;
	private List<PriceReduction> priceReductions;
	private LocalDate creationDate;
	private String creator;
	
}
