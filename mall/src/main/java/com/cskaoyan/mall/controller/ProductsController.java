package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Steve
 * @date 2019/9/30-17:28
 */
@RestController
public class ProductsController {

    @Autowired
    ProductServices productServices;

    /*@RequestMapping("admin/goods/list")
    public List<GoodsProduct> queryGoodsProduct(int page,int limit,String add_time,String desc){
        List<GoodsProduct> goodsProductList = productServices.queryGoodsProduct(page,limit,add_time,desc);
        return goodsProductList;
    }*/
}
