package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;

import java.util.List;

/**
 * @author Steve
 * @date 2019/10/5-15:18
 */
public class WechatGoodsList {
    long count;
    List<Category> filterCategoryList;
    List<Goods> goodsList;

    public WechatGoodsList() {
    }

    public WechatGoodsList(long count, List<Category> filterCategoryList, List<Goods> goodsList) {
        this.count = count;
        this.filterCategoryList = filterCategoryList;
        this.goodsList = goodsList;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Category> getFilterCategoryList() {
        return filterCategoryList;
    }

    public void setFilterCategoryList(List<Category> filterCategoryList) {
        this.filterCategoryList = filterCategoryList;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
