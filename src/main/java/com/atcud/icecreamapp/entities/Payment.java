package com.atcud.icecreamapp.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "card_type")
	private String cardType;
	
	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "cvv")
	private String cvv;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "expired_date")
	private Date expiredDate;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="payment",
			   cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	@JsonIgnore
	private List<RecipeOrder> orders;
	
	public Payment() {
		
	}

	public Payment(Long id, String cardType, String cardNumber, String cvv, String name, Date expiredDate,
			Date dateOfBirth) {
		super();
		this.id = id;
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.name = name;
		this.expiredDate = expiredDate;
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", cardType=" + cardType + ", cardNumber=" + cardNumber + ", cvv=" + cvv
				+ ", name=" + name + ", expiredDate=" + expiredDate + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
}
