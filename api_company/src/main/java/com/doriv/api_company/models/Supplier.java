package com.doriv.api_company.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SUPPLIERS")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "ID_SUPPLIER")
	private UUID id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "COUNTRY")
	private String country;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "suppliers")
	@Column(name = "SUPPLIERS")
	private Set<Item> items = new HashSet<Item>();

	public Supplier() {
	}

	public Supplier(String name, String country, Set<Item> items) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.country = country;
		this.items = items;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		if (items.add(item)) {
            item.addSupplier(this);
        }
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

}

