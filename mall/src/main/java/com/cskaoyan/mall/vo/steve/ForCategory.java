package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.Category;

import java.util.List;

/**
 * @author Steve
 * @date 2019/10/1-21:25
 */
public class ForCategory {
    List<CategoryChildren> children;
    String label;
    String value;

    public ForCategory() {
    }


    public ForCategory(List<CategoryChildren> children, String label, String value) {
        this.children = children;
        this.label = label;
        this.value = value;
    }

    public List<CategoryChildren> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryChildren> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
