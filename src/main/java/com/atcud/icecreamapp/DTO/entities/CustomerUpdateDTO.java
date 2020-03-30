package com.atcud.icecreamapp.DTO.entities;

public class CustomerUpdateDTO {

    private CustomerDTO customer;

    private String currentPassword;

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

    public CustomerUpdateDTO(CustomerDTO customerDTO, String currentPassword) {
        this.customer = customerDTO;
        this.currentPassword = currentPassword;
    }
}
