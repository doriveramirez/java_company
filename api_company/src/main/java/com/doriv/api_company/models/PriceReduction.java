package com.doriv.api_company.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "PRICE_REDUCTIONS")
public class PriceReduction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "ID_PRICE_REDUCTION")
	private UUID id;

	@Column(name = "REDUCED_PRICE", precision = 2)
	private double reducedPrice;

	@Column(name = "START_DATE")
	private LocalDate startDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ITEM")
	private Item item;

	public PriceReduction() {
	}

	public PriceReduction(double reducedPrice, LocalDate startDate, Item item) {
		this.reducedPrice = reducedPrice;
		this.startDate = startDate;
		this.item = item;
	}

	public UUID getId() {
		return id;
	}

	public void setId() {
		this.id = UUID.randomUUID();
	}

	public double getReducedPrice() {
		return reducedPrice;
	}

	public void setReducedPrice(double reducedPrice) {
		this.reducedPrice = reducedPrice;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "PriceReduction [id=" + id + ", reducedPrice=" + reducedPrice + ", startDate=" + startDate + ", item="
				+ item + "]";
	}

}
