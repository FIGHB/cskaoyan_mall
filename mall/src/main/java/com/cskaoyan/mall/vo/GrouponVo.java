package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;

public class GrouponVo {
    private Goods goods;
    private Groupon groupon;
    private GrouponRules rules;
    private String [] subGroupons={};

    public GrouponVo(Goods goods, Groupon groupon, GrouponRules rules) {
        this.goods = goods;
        this.groupon = groupon;
        this.rules = rules;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public GrouponRules getRules() {
        return rules;
    }

    public void setRules(GrouponRules rules) {
        this.rules = rules;
    }

    public String[] getSubGroupons() {
        return subGroupons;
    }

    public void setSubGroupons(String[] subGroupons) {
        this.subGroupons = subGroupons;
    }

    public GrouponVo() {

    }
}
