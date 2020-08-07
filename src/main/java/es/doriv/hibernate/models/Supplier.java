package es.doriv.hibernate.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLIER")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name ="ID_SUPPLIER")
	private Long id;
	
	@Column(name ="NAME")
	private String name;
	
	@Column(name ="COUNTRY")
	private String country;

	public Supplier() {
	}

	public Supplier(Long id, String name, String country) {
		this.id = id;
		this.name = name;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

}
