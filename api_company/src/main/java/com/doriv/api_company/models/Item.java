package com.doriv.api_company.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ITEMS")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "ID_ITEM")
	private UUID id;

	@Column(name = "CODE")
	private int code;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE")
	private double price;

	@Column(name = "STATE")
	private boolean state;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ITEMS_SUPPLIERS", joinColumns = { @JoinColumn(name = "ID_ITEM") }, inverseJoinColumns = {
			@JoinColumn(name = "ID_SUPPLIER") })
	@Column(name = "SUPPLIERS")
	private Set<Supplier> suppliers = new HashSet<Supplier>();

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	@Column(name = "PRICE_REDUCTIONS")
	private Set<PriceReduction> priceReductions = new HashSet<PriceReduction>();

	@Column(name = "CREATION_DATE")
	private LocalDate creationDate;

	@Column(name = "CREATOR")
	private String creator;

	public Item() {
	}
	
	public Item(int code, String description, double price, boolean state, Set<Supplier> suppliers,
			Set<PriceReduction> priceReductions, LocalDate creationDate, String creator) {
		this.id = UUID.randomUUID();
		this.code = code;
		this.description = description;
		this.price = price;
		this.state = state;
		this.suppliers = suppliers;
		this.priceReductions = priceReductions;
		this.creationDate = creationDate;
		this.creator = creator;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	
	public void addSupplier(Supplier supplier) {
		if (suppliers.add(supplier)) {
	        supplier.addItem(this);
	      }
	}
	
	public void addPriceReduction(PriceReduction priceReduction) {
		this.priceReductions.add(priceReduction);
	}

	public Set<PriceReduction> getPriceReductions() {
		return priceReductions;
	}

	public void setPriceReductions(Set<PriceReduction> priceReductions) {
		this.priceReductions = priceReductions;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", code=" + code + ", description=" + description + ", price=" + price + ", state="
				+ state + ", suppliers=" + suppliers + ", priceReductions=" + priceReductions + ", creationDate="
				+ creationDate + ", creator=" + creator + "]";
	}

}