package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.services.GoodsServices;
import com.cskaoyan.mall.utils.SteveListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.SteveGoods;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Steve
 * @date 2019/9/30-20:26
 */
@RestController
public class GoodsController {
    @Autowired
    GoodsServices goodsServices;

    @RequestMapping("admin/goods/list")
    public BaseRespVo queryGoodsProduct(SteveGoods steveGoods){ //前端如果是json,需要写@RequestBody
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        //查询商品
        List<Goods> goodstList = goodsServices.queryGoods(steveGoods);
        //查询一下总数 总数就是list中个个数
        long total = goodsServices.queryGoodsNum(steveGoods);

        SteveListBean steveListBean = new SteveListBean(goodstList,total);
        BaseRespVo baseRespVo = BaseRespVo.ok(steveListBean);
        return baseRespVo;
    }
}
