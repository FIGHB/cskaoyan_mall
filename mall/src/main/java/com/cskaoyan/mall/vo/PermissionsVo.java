package com.cskaoyan.mall.vo;

import java.util.List;

public class PermissionsVo {
    private List<String> peimissions;
    private int roleId;

    public List<String> getPeimissions() {
        return peimissions;
    }

    public void setPeimissions(List<String> peimissions) {
        this.peimissions = peimissions;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
