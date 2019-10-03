package com.cskaoyan.mall.vo;

import com.cskaoyan.mall.bean.Brand;

import java.util.List;

public class Categorys {
    List<CategoryGuo> categoryList;
    List<BrandShow> brandList;

    public List<BrandShow> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandShow> brandList) {
        this.brandList = brandList;
    }

    public void setCategoryList(List<CategoryGuo> categoryList) {
        this.categoryList = categoryList;
    }

    public List<CategoryGuo> getCategoryList() {
        return categoryList;
    }

    @Override
    public String toString() {
        return "Categorys{" +
                "categoryList=" + categoryList +
                ", brandList=" + brandList +
                '}';
    }
}
