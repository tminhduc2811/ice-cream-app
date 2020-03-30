package com.atcud.icecreamapp.DTO.entities;

public class UserUpdateDTO {

    private UserDTO user;

    private String currentPassword;

    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public UserUpdateDTO(UserDTO user, String currentPassword, String newPassword) {
        this.user = user;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
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
