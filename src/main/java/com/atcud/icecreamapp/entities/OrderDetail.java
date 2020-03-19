package com.atcud.icecreamapp.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	// An order has only one detail table, so the relation is one to one
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="order_id")
	private RecipeOrder order;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="recipe_id")
	private Recipe recipe;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "price")
	private Float price;
	
	@Column(name = "notes")
	private String notes;
	
	public OrderDetail() {
		
	}


	public OrderDetail(Long id, int quantity, Float price, String notes) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.notes = notes;
	}


	public RecipeOrder getOrder() {
		return order;
	}

	public void setOrder(RecipeOrder order) {
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


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
}
