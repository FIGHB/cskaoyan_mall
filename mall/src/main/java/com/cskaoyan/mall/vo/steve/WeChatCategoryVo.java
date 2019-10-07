package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.Category;

import java.util.List;

/**
 * @author Steve
 * @date 2019/10/5-20:54
 */
public class WeChatCategoryVo {
    List<Category> brotherCategory;
    Category currentCategory;
    Category parentCategory;

    public WeChatCategoryVo() {
    }

    public WeChatCategoryVo(List<Category> brotherCategory, Category currentCategory, Category parentCategory) {
        this.brotherCategory = brotherCategory;
        this.currentCategory = currentCategory;
        this.parentCategory = parentCategory;
    }

    public List<Category> getBrotherCategory() {
        return brotherCategory;
    }

    public void setBrotherCategory(List<Category> brotherCategory) {
        this.brotherCategory = brotherCategory;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}
