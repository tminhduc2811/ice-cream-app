package com.atcud.icecreamapp.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id")
	private String cardType;
	
	@Column(name = "id")
	private String cardNumber;
	
	@Column(name = "id")
	private String cvv;
	
	@Column(name = "id")
	private String name;
	
	@Column(name = "id")
	private Date expiredDate;
	
	@Column(name = "id")
	private Date dateOfBirth;
	
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
