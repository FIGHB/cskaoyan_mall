package com.cskaoyan.mall.vo;

import java.util.List;

public class PermissionsVo {
    private List<String> permissions;
    private int roleId;

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
