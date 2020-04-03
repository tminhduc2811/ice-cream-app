package com.atcud.icecreamapp.DTO.entities;

public class CustomerUpdateDTO {

    private CustomerDTO customer;

    private String currentPassword;

    private String newPassword;

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public CustomerUpdateDTO(CustomerDTO customer, String currentPassword, String newPassword) {
        this.customer = customer;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
