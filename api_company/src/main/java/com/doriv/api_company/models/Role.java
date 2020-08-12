package com.doriv.api_company.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "ROLES")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "ID_USER")
	private UUID id;

	@Column(name = "NAME")
	private String name;

	@ManyToMany( mappedBy = "roles", cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@Column(name = "USERS")
	private List<User> users = new ArrayList<User>();

	public UUID getId() {
		return id;
	}

	public void setId() {
		this.id = UUID.randomUUID();
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

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Role() {
		super();
	}

	public Role(String name, List<User> users) {
		super();
		this.name = name;
		this.users = users;
	}

	public Role(String name) {
		super();
		this.name = name;
	}
	
	

	
}
