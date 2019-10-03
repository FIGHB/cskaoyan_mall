package com.cskaoyan.mall.controller.steve;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsAttribute;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsSpecification;
import com.cskaoyan.mall.service.steve.GoodsServices;
import com.cskaoyan.mall.utils.SteveListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.NewGoodAddVO;
import com.cskaoyan.mall.vo.steve.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
        Date date = new Date();
        newGoodAddVO.getGoods().setAddTime(date);
        for (GoodsAttribute attribute : newGoodAddVO.getAttributes()) {
            attribute.setAddTime(date);
        }
        for (GoodsProduct product : newGoodAddVO.getProducts()) {
            product.setAddTime(date);
        }
        for (GoodsSpecification specification : newGoodAddVO.getSpecifications()) {
            specification.setAddTime(date);
        }
        System.out.println(newGoodAddVO);

        int flag = goodsServices.addGoods(newGoodAddVO);
        if (flag == 0){
            return  BaseRespVo.ok(null);
        }else {
            return BaseRespVo.err(null);
        }
    }

    @RequestMapping("/admin/goods/delete")
    public BaseRespVo deleteGoods(@RequestBody Goods goods){
        goodsServices.deleteGoodsById(goods.getId());
        return BaseRespVo.ok(null);
    }

    /*@RequestMapping("admin/goods/update")
    public BaseRespVo updateGoods(@RequestBody NewGoodAddVO newGoodAddVO){
        Date date = new Date();
        newGoodAddVO.getGoods().setAddTime(date);
        for (GoodsAttribute attribute : newGoodAddVO.getAttributes()) {
            attribute.setUpdateTime(date);
        }
        for (GoodsProduct product : newGoodAddVO.getProducts()) {
            product.setUpdateTime(date);
        }
        for (GoodsSpecification specification : newGoodAddVO.getSpecifications()) {
            specification.setUpdateTime(date);
        }
        System.out.println(newGoodAddVO);

        int flag = goodsServices.updateGoods(newGoodAddVO);
        if (flag == 0){
            return  BaseRespVo.ok(null);
        }else if (flag == 1){
            return BaseRespVo.err(null);
        }else{
            return BaseRespVo.updateErr(null);
        }
    }*/
}
