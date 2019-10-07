package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Goods;

import java.math.BigDecimal;

public class GrouponDataVO {
   private Goods goods;
   private int goodsId;
   private int groupon_member;
   private BigDecimal discount;
   private BigDecimal groupon_price;


    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getGroupon_price() {
        return groupon_price;
    }

    public void setGroupon_price(BigDecimal groupon_price) {
        this.groupon_price = groupon_price;
    }
}
