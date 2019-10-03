package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsAttribute;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsSpecification;

import java.util.Arrays;
import java.util.List;


public class GoodsDetail {
    int[] categoryIds;
    Goods goods;
    List<GoodsAttribute> attributes;
    List<GoodsSpecification> specifications;
    List<GoodsProduct> products;

    public int[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(int[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<GoodsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<GoodsAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<GoodsSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<GoodsSpecification> specifications) {
        this.specifications = specifications;
    }

    public List<GoodsProduct> getProducts() {
        return products;
    }

    public void setProducts(List<GoodsProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "GoodsDetail{" +
                "categoryIds=" + Arrays.toString(categoryIds) +
                ", goods=" + goods +
                ", attributes=" + attributes +
                ", specifications=" + specifications +
                ", products=" + products +
                '}';
    }
}
