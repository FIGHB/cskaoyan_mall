package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsAttribute;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsSpecification;

import java.util.List;

/**
 * @author Steve
 * @date 2019/10/2-14:07
 */
public class AddGoods {
    List<GoodsAttribute> attributes;
    Goods goods;
    List<GoodsProduct> products;
    List<GoodsSpecification> specifications;

    public AddGoods() {
    }

    public AddGoods(List<GoodsAttribute> attributes, Goods goods,
                    List<GoodsProduct> products, List<GoodsSpecification> specifications) {
        this.attributes = attributes;
        this.goods = goods;
        this.products = products;
        this.specifications = specifications;
    }

    public List<GoodsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<GoodsAttribute> attributes) {
        this.attributes = attributes;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<GoodsProduct> getProducts() {
        return products;
    }

    public void setProducts(List<GoodsProduct> products) {
        this.products = products;
    }

    public List<GoodsSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<GoodsSpecification> specifications) {
        this.specifications = specifications;
    }
}
