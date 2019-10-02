package com.cskaoyan.mall.vo.steve;

/**
 * @author Steve
 * @date 2019/10/2-9:15
 */
public class SteveBrand {
    String value;
    String label;

    public SteveBrand() {
    }

    public SteveBrand(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
