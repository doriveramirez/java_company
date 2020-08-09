package com.doriv.api_company.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TEST")
public class Testt implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "ID_TEST", updatable = false, nullable = false)
	private UUID id;
	
	@Column(name = "NAME")
	private int name;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public Testt() {
		super();
	}

	public Testt(int name) {
		super();
		this.id = UUID.randomUUID();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + "]";
	}
	
}
