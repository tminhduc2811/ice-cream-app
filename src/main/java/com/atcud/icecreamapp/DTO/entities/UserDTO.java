package com.atcud.icecreamapp.DTO.entities;

public class UserDTO {

    private Long id;

    private String userName;

    private String password;

    private String fullName;

    private Short status;

    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserDTO(Long id, String userName, String password, String fullName, Short status, String avatar) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
        this.avatar = avatar;
    }
}
