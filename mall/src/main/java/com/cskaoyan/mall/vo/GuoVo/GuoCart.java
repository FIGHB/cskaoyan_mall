package com.cskaoyan.mall.vo.GuoVo;

public class GuoCart {
    private Integer goodsId;
    private Short number;
    private Integer productId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "GuoCart{" +
                "goodsId=" + goodsId +
                ", number=" + number +
                ", productId=" + productId +
                '}';
    }
}
