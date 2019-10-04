package com.cskaoyan.mall.controller.WXController;

import com.cskaoyan.mall.bean.Goods;

public class GrouponGood {
   private Goods goods;
   private int groupon_member;
   private double groupon_price;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getGroupon_member() {
        return groupon_member;
    }

    public void setGroupon_member(int groupon_member) {
        this.groupon_member = groupon_member;
    }

    public double getGroupon_price() {
        return groupon_price;
    }

    public void setGroupon_price(double groupon_price) {
        this.groupon_price = groupon_price;
    }
}
