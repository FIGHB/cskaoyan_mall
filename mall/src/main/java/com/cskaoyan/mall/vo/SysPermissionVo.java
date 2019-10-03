package com.cskaoyan.mall.vo;

import java.util.List;

/**
 * @author 李锐
 */
public class SysPermissionVo {
    private int pid;
    private int parent_id;
    private String id;
    private String label;
    private String api;
    private List<SysPermissionVo> children;

    public List<SysPermissionVo> getChildren() {
        return children;
    }

    public void setChildren(List<SysPermissionVo> children) {
        this.children = children;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
