package com.atcud.icecreamapp.DTO.entities;

import java.sql.Timestamp;

public class RecipeDTO {
    private Long id;

    private Long userId;

    private Long icecreamId;

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

    public RecipeDTO(Long id, Long userId, Long icecreamId, String title, String description, Float price, Short status, int viewCount, String image, String details, Timestamp uploadedDate) {
        this.id = id;
        this.userId = userId;
        this.icecreamId = icecreamId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIcecreamId() {
        return icecreamId;
    }

    public void setIcecreamId(Long icecreamId) {
        this.icecreamId = icecreamId;
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
