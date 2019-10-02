package com.cskaoyan.mall.service.steve;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.steve.SteveAddGoods;
import com.cskaoyan.mall.mapper.steve.SteveBrandMapper;
import com.cskaoyan.mall.vo.NewGoodAddVO;
import com.cskaoyan.mall.vo.steve.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    SteveAddGoods steveAddGoods;

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
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn()).andNameLike("%" + steveGoods.getName() + "%");
        } else if (((steveGoods.getGoodsSn() == null ) || ("".equals(steveGoods.getGoodsSn().trim())))
                && (steveGoods.getName() != null) && (!"".equals(steveGoods.getName().trim()))) {
            criteria.andNameLike("%" + steveGoods.getName() + "%");
        } else if ((steveGoods.getGoodsSn() != null) && (!"".equals(steveGoods.getGoodsSn().trim()))
                && ((steveGoods.getName() == null) || "".equals(steveGoods.getName().trim()))) {
            criteria.andGoodsSnEqualTo(steveGoods.getGoodsSn());
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

        //然后遍历catagoryList,根据每一个id,查出来他的子id
        /*for (ForCategory category : categoryLists) {
            //根据每一个id,查出来所有子类
            List<CategoryChildren> categoryChildrenList = steveBrandMapper.queryCategoryChildren(category.getValue());
            category.setChildren(categoryChildrenList);
        }*/
        return categoryLists;
    }

    @Transactional
    @Override
    public void addGoods(NewGoodAddVO newGoodAddVO) {
        int goodId = Integer.parseInt(newGoodAddVO.getGoods().getGoodsSn());
        goodsMapper.insertAttributes(newGoodAddVO.getAttributes(), goodId);
        goodsMapper.insertGoods(newGoodAddVO.getGoods(),goodId);
        goodsMapper.insertProduct(newGoodAddVO.getProducts(),goodId);
        goodsMapper.insertSpec(newGoodAddVO.getSpecifications(),goodId);
    }

    @Override
    public void deleteGoodsById(Integer id) {
        goodsMapper.deleteGoodsById(id);
    }
}
