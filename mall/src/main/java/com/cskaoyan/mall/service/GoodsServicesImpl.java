package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.vo.SteveGoods;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Steve
 * @date 2019/9/30-20:28
 */
@Service
public class GoodsServicesImpl implements GoodsServices {
    @Autowired
    GoodsMapper goodsMapper;

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
}
