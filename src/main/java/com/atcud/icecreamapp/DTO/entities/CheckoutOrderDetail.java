package com.atcud.icecreamapp.DTO.entities;

public class CheckoutOrderDetail {
    private Long recipeId;
    private int quantity;
    private Float total;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
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

    public CheckoutOrderDetail(Long recipeId, int quantity, Float total) {
        this.recipeId = recipeId;
        this.quantity = quantity;
        this.total = total;
    }
}
