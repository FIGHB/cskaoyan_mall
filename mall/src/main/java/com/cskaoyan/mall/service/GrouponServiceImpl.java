package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.mapper.selfmapper.WxfGrouponRulesMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.GrouponVo;
import com.cskaoyan.mall.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class GrouponServiceImpl implements GrouponService {
    @Autowired
    WxfGrouponRulesMapper wxfGrouponRulesMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public BaseRespVo selectAllGroupon(int page, int limit, GrouponRules grouponRules) {
        PageHelper.startPage(page,limit);
        GrouponRules[] grouponRulesResp = wxfGrouponRulesMapper.queryGrouponByCondition(grouponRules);
        long total = wxfGrouponRulesMapper.queryTotal(grouponRules);
        return BaseRespVo.ok(new PageVo<>(grouponRulesResp,total));
    }

    @Override
    public BaseRespVo update(GrouponRules grouponRules) {
        grouponRulesMapper.updateByPrimaryKeySelective(grouponRules);
        GrouponRules grouponRulesResp  = grouponRulesMapper.selectByPrimaryKey(grouponRules.getId());
        return BaseRespVo.ok(grouponRulesResp);
    }

    @Override
    public BaseRespVo delete(GrouponRules grouponRules) {
        grouponRulesMapper.deleteByPrimaryKey(grouponRules.getId());
        return BaseRespVo.ok(null);
    }

    @Override
    public BaseRespVo create(GrouponRules grouponRules) {
        Goods goods = goodsMapper.selectByPrimaryKey(grouponRules.getGoodsId());
        if(goods==null){
            return BaseRespVo.fail(401,"商品不存在");
        }
        if(grouponRules.getExpireTime()==null||
                grouponRules.getDiscount()==null||grouponRules.getDiscountMember()==null){
            return BaseRespVo.fail(401,"参数不对");
        }
        grouponRules.setGoodsName(goods.getName());
        grouponRules.setPicUrl(goods.getPicUrl());
        Date date = new Date();
        grouponRules.setAddTime(date);
        grouponRules.setUpdateTime(date);
        grouponRulesMapper.insertSelective(grouponRules);
        GrouponRules grouponRulesResp = wxfGrouponRulesMapper.selectLastInsert();
        return BaseRespVo.ok(grouponRulesResp);
    }

    @Override
    public BaseRespVo listRecord(int page, int limit, GrouponRules grouponRules) {
        PageHelper.startPage(page,limit);
        GrouponVo[] grouponVos = wxfGrouponRulesMapper.queryGrouponVo(grouponRules);
        /*PageInfo<GrouponVo> tPageInfo = new PageInfo<GrouponVo>(Arrays.asList(grouponVos));
        long total = tPageInfo.getTotal();*/
        long total = wxfGrouponRulesMapper.queryGrouponVoTotal(grouponRules);
        return BaseRespVo.ok(new PageVo<>(grouponVos,total));
    }
}
