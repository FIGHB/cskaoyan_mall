package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsAttribute;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsSpecification;

import java.util.List;
import java.util.jar.Attributes;

/**
 * @author Steve
 * @date 2019/10/2-14:07
 */
public class AddGoods {
    GoodsAttributeForAddGoods attributes;
    GoodsForAddGoods goods;
    GoodsProductForAddGoods products;
    GoodsSpecificationForAddGoods specifications;

    public AddGoods() {
    }

    public AddGoods(GoodsAttributeForAddGoods attributes,
                    GoodsForAddGoods goods,
                    GoodsProductForAddGoods products,
                    GoodsSpecificationForAddGoods specifications) {
        this.attributes = attributes;
        this.goods = goods;
        this.products = products;
        this.specifications = specifications;
    }

    public GoodsAttributeForAddGoods getAttributes() {
        return attributes;
    }

    public void setAttributes(GoodsAttributeForAddGoods attributes) {
        this.attributes = attributes;
    }

    public GoodsForAddGoods getGoods() {
        return goods;
    }

    public void setGoods(GoodsForAddGoods goods) {
        this.goods = goods;
    }

    public GoodsProductForAddGoods getProducts() {
        return products;
    }

    public void setProducts(GoodsProductForAddGoods products) {
        this.products = products;
    }

    public GoodsSpecificationForAddGoods getSpecifications() {
        return specifications;
    }

    public void setSpecifications(GoodsSpecificationForAddGoods specifications) {
        this.specifications = specifications;
    }
}
