package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.GoodsSpecification;

import java.util.List;

/**
 * @author Steve
 * @date 2019/10/5-23:16
 */
public class WeChatSpecVo {
    String name;
    List<GoodsSpecification> valueList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodsSpecification> getValueList() {
        return valueList;
    }

    public void setValueList(List<GoodsSpecification> valueList) {
        this.valueList = valueList;
    }
}
