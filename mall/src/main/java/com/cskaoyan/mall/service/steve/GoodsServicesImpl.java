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
import com.sun.org.apache.bcel.internal.generic.NEW;
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
        goodsMapper.insertAttributes(newGoodAddVO.getAttributes(), goodId);
        try {
            goodsMapper.insertGoods(newGoodAddVO.getGoods(), goodId);
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
        goodsMapper.insertProduct(newGoodAddVO.getProducts(), goodId);
        goodsMapper.insertSpec(newGoodAddVO.getSpecifications(), goodId);
        return 0;
    }

    @Override
    public void deleteGoodsById(Integer id) {
        goodsMapper.deleteGoodsById(id);
    }

    /*@Transactional
    @Override
    public int updateGoods(NewGoodAddVO newGoodAddVO) {
        int goodId = Integer.parseInt(newGoodAddVO.getGoods().getGoodsSn());

        for (GoodsAttribute attribute : newGoodAddVO.getAttributes()) {
            goodsAttributeMapper.updateByPrimaryKey(attribute);
        }

        if (newGoodAddVO.getAttributes().get(0).equals(newGoodAddVO.getGoods().getId())){
            goodsMapper.updateByPrimaryKey(newGoodAddVO.getGoods());
        }else {
            return 2;
        }

        for (GoodsProduct product : newGoodAddVO.getProducts()) {
            goodsProductMapper.updateByPrimaryKey(product);
        }

        for (GoodsSpecification specification : newGoodAddVO.getSpecifications()) {
            goodsSpecificationMapper.updateByPrimaryKey(specification);
        }
        return 0;
    }*/
}
