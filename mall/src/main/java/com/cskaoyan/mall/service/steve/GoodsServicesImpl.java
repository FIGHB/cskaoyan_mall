package com.cskaoyan.mall.service.steve;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.GoodsAttributeMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.mapper.GoodsSpecificationMapper;
import com.cskaoyan.mall.mapper.steve.SteveAddGoodsMapper;
import com.cskaoyan.mall.mapper.steve.SteveBrandMapper;
import com.cskaoyan.mall.vo.steve.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
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
    //@Autowired
    //SteveAddGoodsMapper steveAddGoodsMapper;

    //下面的用逆向工程里面的自动生成
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Override
    public List<Goods> queryGoods(SteveGoods steveGoods) {
        PageHelper.startPage(steveGoods.getPage(), steveGoods.getLimit());
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause("add_time desc");
        //这玩意是内部类
        GoodsExample.Criteria criteria = goodsExample.createCriteria();

        //这里做个逻辑判断  这里要判断,既不是空字符串,又不为null,这是两种情况

        if ((steveGoods.getGoodsSn() != null && (!"".equals(steveGoods.getGoodsSn().trim())))
                && (steveGoods.getName() != null) && (!"".equals(steveGoods.getName().trim()))) {
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn()).andNameLike("%" + steveGoods.getName() + "%").andDeletedEqualTo(false);
        } else if (((steveGoods.getGoodsSn() == null ) || ("".equals(steveGoods.getGoodsSn().trim())))
                && (steveGoods.getName() != null) && (!"".equals(steveGoods.getName().trim()))) {
            criteria.andNameLike("%" + steveGoods.getName() + "%").andDeletedEqualTo(false);
        } else if ((steveGoods.getGoodsSn() != null) && (!"".equals(steveGoods.getGoodsSn().trim()))
                && ((steveGoods.getName() == null) || "".equals(steveGoods.getName().trim()))) {
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn()).andDeletedEqualTo(false);
        }

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
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn()).andNameLike("%" + steveGoods.getName() + "%");
        } else if (((steveGoods.getGoodsSn() == null ) || ("".equals(steveGoods.getGoodsSn().trim())))
                && (steveGoods.getName() != null) && (!"".equals(steveGoods.getName().trim()))) {
            criteria.andNameLike("%" + steveGoods.getName() + "%");
        } else if ((steveGoods.getGoodsSn() != null) && (!"".equals(steveGoods.getGoodsSn().trim()))
                && ((steveGoods.getName() == null) || "".equals(steveGoods.getName().trim()))) {
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn());
        }

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
        //分两步查 用分次查询
        //首先查出来category 根据数据库中的pid等于0,查出所有的分类
        List<ForCategory> categoryLists = steveBrandMapper.queryCategory(0);

        return categoryLists;
    }

    @Override
    public boolean addGoods(AddGoods addGoods) {
        //这玩意是内部类
        //---------------------------------------
        //每个里面呀加入一个goodid time deleted
        //-----------------------------------------


        //这个方法将数据分可,分别插入各个对应的表中


        //1.将数据插入goods表中  这里需要用到typehander 要返回最后插入以后的id,给下面用
        //steveAddGoodsMapper.insertGoods(addGoods.getGoods());
        System.out.println(addGoods.getGoods());
        goodsMapper.steveInsert(addGoods.getGoods());
        System.out.println(addGoods.getGoods());


        //2.将数据插入good_attributes表中
        //steveAddGoodsMapper.insertGoodsAttributes(addGoods.getAttributes());
        for (GoodsAttribute attribute : addGoods.getAttributes()) {
            goodsAttributeMapper.insert(attribute);
        }

        //3.将数据插入products表中  这里也需要用到typehander
        //steveAddGoodsMapper.insertProducts(addGoods.getProducts());
        for (GoodsProduct product : addGoods.getProducts()) {
            goodsProductMapper.insert(product);

        }

        //4.将数据插入specifications表中
        //steveAddGoodsMapper.insertGoodsSpecifications(addGoods.getSpecifications());
        for (GoodsSpecification specification : addGoods.getSpecifications()) {
            goodsSpecificationMapper.insert(specification);
        }
        return true;
    }
}
