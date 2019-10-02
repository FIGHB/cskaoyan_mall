package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Category;

import java.util.List;

/**
 * @author Steve
 * @date 2019/10/1-20:44
 */
public class BrandListAndCategoryList {
    List<SteveBrand> brandList;
    List<ForCategory> categoryList;

    public BrandListAndCategoryList() {
    }

    public BrandListAndCategoryList(List<SteveBrand> brandList, List<ForCategory> categoryList) {
        this.brandList = brandList;
        this.categoryList = categoryList;
    }

    public List<SteveBrand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<SteveBrand> brandList) {
        this.brandList = brandList;
    }

    public List<ForCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ForCategory> categoryList) {
        this.categoryList = categoryList;
    }
}
