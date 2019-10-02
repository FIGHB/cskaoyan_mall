package com.cskaoyan.mall.vo.MallBean;

import java.util.List;

public class RegionBean {
  private int id;
  private int pid;
  private String name;
  private int type;
  private int code;
  private List children;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
