package com.cskaoyan.mall.controller.steve;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.steve.GoodsServices;
import com.cskaoyan.mall.utils.SteveListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.NewGoodAddVO;
import com.cskaoyan.mall.vo.steve.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("admin/goods/catAndBrand")
    public BaseRespVo queryBrandListAndCategoryList(){
       BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();

        //首先查询brandList
        List<SteveBrand> brandList = goodsServices.queryBrand();
        //首先查询categoryList
        List<ForCategory> categoryList = goodsServices.queryCategory();

        //这里引入一个新的BrandListAndCategoryList
        BrandListAndCategoryList brandListAndCategoryList = new BrandListAndCategoryList(brandList, categoryList);

        BaseRespVo baseRespVo = BaseRespVo.ok(brandListAndCategoryList);
        return baseRespVo;
    }

    /**
     * 添加商品
     * @return
     */
    @RequestMapping("admin/goods/create")
    public BaseRespVo addGoods(@RequestBody NewGoodAddVO newGoodAddVO){
        goodsServices.addGoods(newGoodAddVO);
        return  BaseRespVo.ok(null);
    }

    @RequestMapping("/admin/goods/delete")
    public BaseRespVo deleteGoods(@RequestBody Goods goods){
        goodsServices.deleteGoodsById(goods.getId());
        return BaseRespVo.ok(null);
    }
}
