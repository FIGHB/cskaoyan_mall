package com.cskaoyan.mall.services;

import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsProductExample;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Steve
 * @date 2019/9/30-17:16
 */
@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Override
    public List<GoodsProduct> queryGoodsProduct(int start, int limit, String add_time, String desc) {
        return null;
    }
}
