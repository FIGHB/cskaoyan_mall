package com.cskaoyan.mall.service.steve;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.GoodsAttributeMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.mapper.GoodsSpecificationMapper;
import com.cskaoyan.mall.mapper.steve.SteveBrandMapper;
import com.cskaoyan.mall.vo.NewGoodAddVO;
import com.cskaoyan.mall.vo.steve.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.System;
import java.util.Date;
import java.util.List;

/**
 * @author Steve
 * @date 2019/9/30-20:28
 */
@Service
public class GoodsServicesImpl implements GoodsServices {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    SteveBrandMapper steveBrandMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public List<Goods> queryGoods(SteveGoods steveGoods) {
        PageHelper.startPage(steveGoods.getPage(), steveGoods.getLimit(), steveGoods.getSort() + " " + steveGoods.getOrder());
        GoodsExample goodsExample = new GoodsExample();
        //goodsExample.setOrderByClause("add_time desc");
        //这玩意是内部类
        GoodsExample.Criteria criteria = goodsExample.createCriteria();

        //这里做个逻辑判断  这里要判断,既不是空字符串,又不为null,这是两种情况

        if ((steveGoods.getGoodsSn() != null && (!"".equals(steveGoods.getGoodsSn().trim())))
                && (steveGoods.getName() != null) && (!"".equals(steveGoods.getName().trim()))) {
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn()).andNameLike("%" + steveGoods.getName() + "%").andDeletedEqualTo(false);
        } else if (((steveGoods.getGoodsSn() == null) || ("".equals(steveGoods.getGoodsSn().trim())))
                && (steveGoods.getName() != null) && (!"".equals(steveGoods.getName().trim()))) {
            criteria.andNameLike("%" + steveGoods.getName() + "%").andDeletedEqualTo(false);
        } else if ((steveGoods.getGoodsSn() != null) && (!"".equals(steveGoods.getGoodsSn().trim()))
                && ((steveGoods.getName() == null) || "".equals(steveGoods.getName().trim()))) {
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn()).andDeletedEqualTo(false);
        }
        criteria.andDeletedEqualTo(false);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList;
    }

    @Override
    public long queryGoodsNum(SteveGoods steveGoods) {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause("add_time desc");

        //这玩意是内部类
        GoodsExample.Criteria criteria = goodsExample.createCriteria();

        //这里做个逻辑判断  这里要判断,既不是空字符串,又不为null,这是两种情况

        if ((steveGoods.getGoodsSn() != null && (!"".equals(steveGoods.getGoodsSn().trim())))
                && (steveGoods.getName() != null) && (!"".equals(steveGoods.getName().trim()))) {
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn()).andNameLike("%" + steveGoods.getName() + "%").andDeletedEqualTo(false);
        } else if (((steveGoods.getGoodsSn() == null) || ("".equals(steveGoods.getGoodsSn().trim())))
                && (steveGoods.getName() != null) && (!"".equals(steveGoods.getName().trim()))) {
            criteria.andNameLike("%" + steveGoods.getName() + "%").andDeletedEqualTo(false);
        } else if ((steveGoods.getGoodsSn() != null) && (!"".equals(steveGoods.getGoodsSn().trim()))
                && ((steveGoods.getName() == null) || "".equals(steveGoods.getName().trim()))) {
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn()).andDeletedEqualTo(false);
        }
        criteria.andDeletedEqualTo(false);

        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        long totals = goodsList.size();

        //long totals = goodsMapper.countByExample(goodsExample);
        return totals;
    }

    @Override
    public List<SteveBrand> queryBrand() {
        List<SteveBrand> brandList = steveBrandMapper.queryBrand();
        return brandList;
    }

    @Override
    public List<ForCategory> queryCategory() {
        //首先查出来category 根据数据库中的pid等于0,查出所有的分类
        List<ForCategory> categoryLists = steveBrandMapper.queryCategory(0);
        return categoryLists;
    }

    @Transactional
    @Override
    public int addGoods(NewGoodAddVO newGoodAddVO) {
        int goodId = Integer.parseInt(newGoodAddVO.getGoods().getGoodsSn());
        if (!newGoodAddVO.getAttributes().isEmpty()) {
            goodsMapper.insertAttributes(newGoodAddVO.getAttributes(), goodId);

        }
        //数据校验
        try {
            Goods goods = newGoodAddVO.getGoods();
            if (goods.getGoodsSn() != null && goods.getName() != null && goods.getCategoryId() != null
                    && goods.getBrandId() != null && goods.getGallery() != null && goods.getKeywords() != null
                    && goods.getPicUrl() != null && goods.getCounterPrice() != null
                    && goods.getRetailPrice() != null && goods.getUnit() != null && goods.getDetail() != null
                    && goods.getBrief() != null
            ) {
                goodsMapper.insertGoods(newGoodAddVO.getGoods(), goodId);
            } else {
                System.out.println(goods);
                return 3;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        if (!newGoodAddVO.getProducts().isEmpty()) {
            goodsMapper.insertProduct(newGoodAddVO.getProducts(), goodId);
        }
        if (!newGoodAddVO.getSpecifications().isEmpty()) {
            goodsMapper.insertSpec(newGoodAddVO.getSpecifications(), goodId);
        }
        return 0;
    }

    @Override
    public void deleteGoodsById(Integer id) {
        goodsMapper.deleteGoodsById(id);
    }

    @Transactional
    @Override
    public int updateGoods(NewGoodAddVO newGoodAddVO) {
        int goodId = Integer.parseInt(newGoodAddVO.getGoods().getGoodsSn());
        if (Integer.parseInt(newGoodAddVO.getGoods().getGoodsSn()) == goodId) {
            Goods goods = newGoodAddVO.getGoods();
            if (goods.getGoodsSn() == null || goods.getName() == null || goods.getCategoryId() == null
                    || goods.getBrandId() == null || goods.getGallery() == null || goods.getKeywords() == null
                    || goods.getPicUrl() == null || goods.getCounterPrice() == null
                    || goods.getRetailPrice() == null || goods.getUnit() == null || goods.getDetail() == null
                    || goods.getBrief() == null
            ) {
                return 3;
            } else {
                newGoodAddVO.getGoods().setUpdateTime(new Date());
                goodsMapper.updateByPrimaryKey(newGoodAddVO.getGoods());
            }
        }

        //对product进行操作
        List<GoodsProduct> goodsProductList = null;
        if (newGoodAddVO.getProducts().get(0).getGoodsId() != null) {
            GoodsProductExample goodsProductExample = new GoodsProductExample();
            GoodsProductExample.Criteria criteria = goodsProductExample.createCriteria();
            criteria.andGoodsIdEqualTo(goodId);
            goodsProductList = goodsProductMapper.selectByExample(goodsProductExample);
            //还需要将查出来的所有的数据,deleted写成0
            for (GoodsProduct goodsProduct : goodsProductList) {
                goodsProduct.setDeleted(true);
                goodsProductMapper.updateByPrimaryKey(goodsProduct);
            }
            //这里判断是要插入还是update
            for (GoodsProduct goodsProduct : newGoodAddVO.getProducts()) {
                if (goodsProduct.getAddTime() != null) {
                    //goodsProduct.setDeleted(false);
                    goodsProduct.setUpdateTime(new Date());
                    goodsProductMapper.updateByPrimaryKey(goodsProduct);
                } else {
                    goodsProduct.setGoodsId(goodId);
                    goodsProduct.setAddTime(new Date());
                    goodsProduct.setDeleted(false);
                    goodsProductMapper.insert(goodsProduct);
                }
            }
        }

        //对规格进行操作
        List<GoodsSpecification> goodsSpecificationList = null;
        if (newGoodAddVO.getSpecifications().get(0).getGoodsId() != null) {
            GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
            GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
            criteria.andGoodsIdEqualTo(goodId);
            goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
            //还需要将查出来的所有的数据,deleted写成0
            for (GoodsSpecification goodsSpecification : goodsSpecificationList) {
                goodsSpecification.setDeleted(true);
                goodsSpecificationMapper.updateByPrimaryKey(goodsSpecification);
            }
            //这里判断是要插入还是update
            for (GoodsSpecification goodsSpecification : newGoodAddVO.getSpecifications()) {
                if (goodsSpecification.getAddTime() != null) {
                    //goodsSpecification.setDeleted(false);
                    goodsSpecification.setUpdateTime(new Date());
                    goodsSpecificationMapper.updateByPrimaryKey(goodsSpecification);
                } else {
                    goodsSpecification.setGoodsId(goodId);
                    goodsSpecification.setAddTime(new Date());
                    goodsSpecification.setDeleted(false);
                    goodsSpecificationMapper.insert(goodsSpecification);
                }
            }
        }
        System.out.println(newGoodAddVO);
        //对Attribute进行操作
        List<GoodsAttribute> goodsAttributeList = null;
        if (!newGoodAddVO.getAttributes().isEmpty()) {
            GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
            GoodsAttributeExample.Criteria criteria = goodsAttributeExample.createCriteria();
            criteria.andGoodsIdEqualTo(goodId);
            goodsAttributeList = goodsAttributeMapper.selectByExample(goodsAttributeExample);
            //还需要将查出来的所有的数据,deleted写成1
            for (GoodsAttribute goodsAttribute : goodsAttributeList) {
                goodsAttribute.setDeleted(true);
                goodsAttributeMapper.updateByPrimaryKey(goodsAttribute);
            }
            //这里判断是要插入还是update
            for (GoodsAttribute goodsAttribute : newGoodAddVO.getAttributes()) {
                if (goodsAttribute.getAddTime() != null) {
                    goodsAttribute.setUpdateTime(new Date());
                    goodsAttributeMapper.updateByPrimaryKey(goodsAttribute);
                } else {
                    goodsAttribute.setGoodsId(goodId);
                    goodsAttribute.setAddTime(new Date());
                    goodsAttribute.setDeleted(false);
                    goodsAttributeMapper.insert(goodsAttribute);
                }
            }
        }
        System.out.println(newGoodAddVO);
        return 0;
    }
}

