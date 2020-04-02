package com.atcud.icecreamapp.DTO.entities;

import com.atcud.icecreamapp.entities.Icecream;

import java.sql.Timestamp;

public class RecipeDTO {
    private Long id;

    private Icecream icecream;

    private UserDTO user;

    private String title;

    private String description;

    private Float price;

    private Short status;

    private int viewCount;

    private String image;

    private String details;

    private Timestamp uploadedDate;

    public RecipeDTO() {

    }

    public Icecream getIcecream() {
        return icecream;
    }

    public void setIcecream(Icecream icecream) {
        this.icecream = icecream;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RecipeDTO(Long id, Icecream icecream, UserDTO user, String title, String description, Float price, Short status, int viewCount, String image, String details, Timestamp uploadedDate) {
        this.id = id;
        this.icecream = icecream;
        this.user = user;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.viewCount = viewCount;
        this.image = image;
        this.details = details;
        this.uploadedDate = uploadedDate;
    }

    public RecipeDTO(Long id, String title, String description, Float price, Short status, int viewCount, String image, String details, Timestamp uploadedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
        this.viewCount = viewCount;
        this.image = image;
        this.details = details;
        this.uploadedDate = uploadedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Timestamp uploadedDate) {
        this.uploadedDate = uploadedDate;
    }
}
