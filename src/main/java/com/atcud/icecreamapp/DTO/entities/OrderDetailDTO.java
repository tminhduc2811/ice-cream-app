package com.atcud.icecreamapp.DTO.entities;

public class OrderDetailDTO {
    private Long id;

    private RecipeDTO recipe;

    private int quantity;

    private Float total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecipeDTO getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeDTO recipe) {
        this.recipe = recipe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public OrderDetailDTO() {

    }

    public OrderDetailDTO(Long id, RecipeDTO recipe, int quantity, Float total) {
        this.id = id;
        this.recipe = recipe;
        this.quantity = quantity;
        this.total = total;
    }
}
