package com.cskaoyan.mall.vo.GuoVo;

import java.math.BigDecimal;
import java.util.Date;

public class FootprintDetail {
    Integer id;
    BigDecimal retailPrice;
    String name;
    Integer goodsId;
    Date addTime;
    String picUrl;
    String brief;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "FootprintDetail{" +
                "id=" + id +
                ", retailPrice=" + retailPrice +
                ", name='" + name + '\'' +
                ", goodsId=" + goodsId +
                ", addTime=" + addTime +
                ", picUrl='" + picUrl + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
