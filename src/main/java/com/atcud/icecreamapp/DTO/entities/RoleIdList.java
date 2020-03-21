package com.atcud.icecreamapp.DTO.entities;

import java.util.List;

public class RoleIdList {

    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public RoleIdList(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
