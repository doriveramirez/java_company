package es.doriv.hibernate.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRICE_REDUCTION")
public class PriceReduction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name ="ID_PRICE_REDUCTION")
	private Long id;
	
	@Column(name = "REDUCED_PRICE",precision = 2)
	private double reducedPrice;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;

	public PriceReduction() {
	}

	public PriceReduction(Long id, double reducedPrice, LocalDate startDate) {
		this.id = id;
		this.reducedPrice = reducedPrice;
		this.startDate = startDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "PriceReduction [id=" + id + ", reducedPrice=" + reducedPrice + ", startDate=" + startDate + "]";
	}

}
