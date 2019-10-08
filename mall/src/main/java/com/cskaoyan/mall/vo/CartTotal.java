package com.cskaoyan.mall.vo;

import java.math.BigDecimal;

public class CartTotal {
   private float checkedGoodsAmount;
   private int checkedGoodsCount;
   private float goodsAmount;
   private int goodsCount;


    public float getCheckedGoodsAmount() {
        return checkedGoodsAmount;
    }

    public void setCheckedGoodsAmount(float checkedGoodsAmount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
    }

    public float getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(float goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public int getCheckedGoodsCount() {
        return checkedGoodsCount;
    }

    public void setCheckedGoodsCount(int checkedGoodsCount) {
        this.checkedGoodsCount = checkedGoodsCount;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }
}
