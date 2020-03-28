package com.atcud.icecreamapp.DTO.entities;

public class UserUpdateDTO {

    private UserDTO user;

    private String currentPassword;

    public UserUpdateDTO(UserDTO user, String currentPassword) {
        this.user = user;
        this.currentPassword = currentPassword;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}
