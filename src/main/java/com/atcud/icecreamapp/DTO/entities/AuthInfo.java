package com.atcud.icecreamapp.DTO.entities;

import java.util.List;

public class AuthInfo {
    private boolean isLoggedIn;
    private List<String> roles;
    private String username;
    private String avatar;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public AuthInfo(boolean isLoggedIn, List<String> roles, String username, String avatar) {
        this.isLoggedIn = isLoggedIn;
        this.roles = roles;
        this.username = username;
        this.avatar = avatar;
    }
}
