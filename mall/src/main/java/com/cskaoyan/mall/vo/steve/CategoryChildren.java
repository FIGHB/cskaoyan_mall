package com.cskaoyan.mall.vo.steve;

/**
 * @author Steve
 * @date 2019/10/1-22:22
 */
public class CategoryChildren {
    String label;
    String value;

    public CategoryChildren(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public CategoryChildren() {
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
