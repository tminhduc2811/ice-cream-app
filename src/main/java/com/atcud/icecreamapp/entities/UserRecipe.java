package com.atcud.icecreamapp.entities;

public class UserRecipe {

	private Long id;
	
	private String name;
	
	private String image;
	
	private String description;
	
	private String details;
	
	private Long customerId;
	
	private Short prizeStatus;
	
	private Short enableStatus;
	
	public UserRecipe() {
		
	}
	
	public UserRecipe(Long id, String name, String image, String description, String details, Long customerId,
			Short prizeStatus, Short enableStatus) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.details = details;
		this.customerId = customerId;
		this.prizeStatus = prizeStatus;
		this.enableStatus = enableStatus;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Short getPrizeStatus() {
		return prizeStatus;
	}

	public void setPrizeStatus(Short prizeStatus) {
		this.prizeStatus = prizeStatus;
	}

	public Short getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Short enableStatus) {
		this.enableStatus = enableStatus;
	}

	@Override
	public String toString() {
		return "UserRecipe [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description
				+ ", details=" + details + ", customerId=" + customerId + ", prizeStatus=" + prizeStatus
				+ ", enableStatus=" + enableStatus + "]";
	}
	
}
