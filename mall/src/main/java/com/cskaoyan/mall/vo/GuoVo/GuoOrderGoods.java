package com.cskaoyan.mall.vo.GuoVo;

import com.cskaoyan.mall.bean.GrouponRules;

import java.math.BigDecimal;
import java.util.Arrays;

public class GuoOrderGoods {
    private Short number;
    private String picUrl;
    private Integer orderId;
    private Integer goodsId;
    private String[] goodsSpecificationValues;
    private Integer id;
    private String goodsName;
    private BigDecimal retailPrice;


    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String[] getGoodsSpecificationValues() {
        return goodsSpecificationValues;
    }

    public void setGoodsSpecificationValues(String[] goodsSpecificationValues) {
        this.goodsSpecificationValues = goodsSpecificationValues;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public String toString() {
        return "GuoOrderGoods{" +
                "number=" + number +
                ", picUrl='" + picUrl + '\'' +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", goodsSpecificationValues=" + Arrays.toString(goodsSpecificationValues) +
                ", id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", retailPrice=" + retailPrice +
                '}';
    }
}
