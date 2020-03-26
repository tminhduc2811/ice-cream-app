package com.atcud.icecreamapp.DTO.entities;

public class LoginResponseDTO {

    private String userName;

    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponseDTO(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }
}
