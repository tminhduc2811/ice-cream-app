package com.atcud.icecreamapp.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "view_number")
	private Long viewNumber;
	
	@Column(name = "upload_date")
	private Date uploadDate;
	
	@Column(name = "enable_status")
	private Short enableStatus;
	
	public Recipe() {
		
	}

	public Recipe(Long id, String name, String image, String description, String details, String author,
			Long viewNumber, Date uploadDate, Short enableStatus) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.details = details;
		this.author = author;
		this.viewNumber = viewNumber;
		this.uploadDate = uploadDate;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(Long viewNumber) {
		this.viewNumber = viewNumber;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Short enableStatus) {
		this.enableStatus = enableStatus;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", image=" + image + ", description=" + description
				+ ", details=" + details + ", author=" + author + ", viewNumber=" + viewNumber + ", uploadDate="
				+ uploadDate + ", enableStatus=" + enableStatus + "]";
	}
	
	
}
