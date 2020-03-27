package com.atcud.icecreamapp.DTO.entities;

public class AuthResponseDTO {
    private String token;
    private AuthInfo info;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthInfo getInfo() {
        return info;
    }

    public void setInfo(AuthInfo info) {
        this.info = info;
    }

    public AuthResponseDTO(String token, AuthInfo info) {
        this.token = token;
        this.info = info;
    }
}
