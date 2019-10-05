package com.cskaoyan.mall.bean.wxfbean;

import com.cskaoyan.mall.bean.Brand;

import java.util.List;

public class BrandListBean {
    List<Brand> brandList;
    int totalPages;

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
