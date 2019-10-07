package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.Brand;

/**
 * @author Steve
 * @date 2019/10/5-22:42
 */
public class BrandListVo {
    Brand brand;

    public BrandListVo() {
    }

    public BrandListVo(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
