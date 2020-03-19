package com.atcud.icecreamapp.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

	// An order has only one detail table, so the relation is one to one
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
	@Id
	private Order order;
	
	private Recipe recipe;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private Float price;
	
	@Column(name = "notes")
	private String notes;
	
	public OrderDetail() {
		
	}

	public OrderDetail(int quantity, Float price, String notes) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.notes = notes;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "OrderDetail [quantity=" + quantity + ", price=" + price + ", notes=" + notes + "]";
	}
	
	
}
