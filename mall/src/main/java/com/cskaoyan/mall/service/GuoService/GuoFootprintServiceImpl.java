package com.cskaoyan.mall.service.GuoService;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.FootprintExample;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsExample;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.vo.GuoVo.FootprintDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GuoFootprintServiceImpl implements GuoFootprintService {

    @Autowired
    FootprintMapper footprintMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Footprint> getFootprintList() {
        FootprintExample footprintExample = new FootprintExample();
        FootprintExample.Criteria criteria = footprintExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Footprint> footprintList = footprintMapper.selectByExample(footprintExample);
        return footprintList;
    }

    @Override
    public List<FootprintDetail> getFootprintDetailList(List<Footprint> footprintList) {
        GoodsExample goodsExample = new GoodsExample();
        List<FootprintDetail> footprintDetailList=new ArrayList<>();
        for (Footprint footprint : footprintList) {
            GoodsExample.Criteria criteria = goodsExample.createCriteria();
            Integer goodsId = footprint.getGoodsId();
            criteria.andIdEqualTo(goodsId);
            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
            Goods goods = goodsList.get(0);
            FootprintDetail footprintDetail = new FootprintDetail();
            footprintDetail.setId(footprint.getId());
            footprintDetail.setAddTime(footprint.getAddTime());
            footprintDetail.setBrief(goods.getBrief());
            footprintDetail.setGoodsId(goods.getId());
            footprintDetail.setName(goods.getName());
            footprintDetail.setPicUrl(goods.getPicUrl());
            footprintDetail.setRetailPrice(goods.getRetailPrice());
            footprintDetailList.add(footprintDetail);
        }
        return footprintDetailList;
    }
}
