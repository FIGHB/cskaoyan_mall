package com.cskaoyan.mall.bean.wxfbean;

import com.cskaoyan.mall.bean.Category;

import java.util.List;

public class CurrentCategoryBean {
    Category currentCategory;
    List<Category> currentSubCategory;

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public List<Category> getCurrentSubCategory() {
        return currentSubCategory;
    }

    public void setCurrentSubCategory(List<Category> currentSubCategory) {
        this.currentSubCategory = currentSubCategory;
    }
}
