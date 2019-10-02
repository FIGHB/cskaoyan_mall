package com.cskaoyan.mall.service;

import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.selfmapper.LiRuiHomepageMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李锐
 */
@Service
public class LiRuiHomepageServiceImpl implements LiRuiHomepageService {

    @Autowired
    LiRuiHomepageMapper homepageMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    SqlSession sqlSession;

    /*********************************正文*****************************/

    @Override
    public Map<String, Object> getTotal() {
        String goodsTotal  = homepageMapper.countGoodsTotal();
        String orderTotal  = homepageMapper.countOrderTotal();
        String productTotal  = homepageMapper.countProductTotal();
        String userTotal  = homepageMapper.countUserTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsTotal", goodsTotal);
        map.put("orderTotal", orderTotal);
        map.put("productTotal", productTotal);
        map.put("userTotal", userTotal);

        return map;
    }
}
