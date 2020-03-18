package com.atcud.icecreamapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reference")
public class Reference {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reference")
	private Long id;
	
	@Column(name = "monthly_fee")
	private Float monthlyFee;
	
	@Column(name = "yearly_fee")
	private Float yearlyFee;
	
	@Column(name = "book_cost")
	private Float bookCost;
	
	public Reference() {
		
	}

	public Reference(Long id, Float monthlyFee, Float yearlyFee, Float bookCost) {
		super();
		this.id = id;
		this.monthlyFee = monthlyFee;
		this.yearlyFee = yearlyFee;
		this.bookCost = bookCost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(Float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public Float getYearlyFee() {
		return yearlyFee;
	}

	public void setYearlyFee(Float yearlyFee) {
		this.yearlyFee = yearlyFee;
	}

	public Float getBookCost() {
		return bookCost;
	}

	public void setBookCost(Float bookCost) {
		this.bookCost = bookCost;
	}

	@Override
	public String toString() {
		return "Reference [id=" + id + ", monthlyFee=" + monthlyFee + ", yearlyFee=" + yearlyFee + ", bookCost="
				+ bookCost + "]";
	}
	
	
}
