package com.cskaoyan.mall.vo;

import java.util.List;

public class CategoryGuo {
    private int value;
    private String label;
    private List<CategoryGuo> Children;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CategoryGuo> getChildren() {
        return Children;
    }

    public void setChildren(List<CategoryGuo> children) {
        Children = children;
    }

    @Override
    public String toString() {
        return "CategoryGuo{" +
                "value=" + value +
                ", label='" + label + '\'' +
                ", Children=" + Children +
                '}';
    }
}
