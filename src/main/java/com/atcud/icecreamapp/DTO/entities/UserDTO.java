package com.atcud.icecreamapp.DTO.entities;

import com.atcud.icecreamapp.entities.Role;

import java.util.List;

public class UserDTO {

    private Long id;

    private String userName;

    private String fullName;

    private String email;

    private Short status;

    private String avatar;

    private List<Role> roles;

    public UserDTO() {

    }

    public UserDTO(Long id, String userName, String fullName, String email, Short status, String avatar, List<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.status = status;
        this.avatar = avatar;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

}
