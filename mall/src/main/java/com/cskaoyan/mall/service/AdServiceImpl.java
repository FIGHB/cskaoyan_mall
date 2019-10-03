package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.mapper.selfmapper.WxfAdMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdMapper adMapper;
    @Autowired
    WxfAdMapper wxfAdMapper;

    @Override
    @Transactional
    public BaseRespVo insert(Ad ad) {
        Date date = new Date();
        ad.setAddTime(date);
        ad.setUpdateTime(date);
        adMapper.insertSelective(ad);
        Ad adResp = wxfAdMapper.selectLastInsert();
        return BaseRespVo.ok(adResp);
    }

    @Override
    public BaseRespVo update(Ad ad) {
        adMapper.updateByPrimaryKeySelective(ad);
        Ad adResp = adMapper.selectByPrimaryKey(ad.getId());
        return BaseRespVo.ok(adResp);
    }

    @Override
    public BaseRespVo delete(Ad ad) {
        adMapper.deleteByPrimaryKey(ad.getId());
        return BaseRespVo.ok(null);
    }

    @Override
    public BaseRespVo queryByNameAndContent(int start, int limit,Ad ad) {
        PageHelper.startPage(start,limit);
        Ad[] ads = wxfAdMapper.queryByNameAndContent(ad);
        long total = wxfAdMapper.queryTotal(ad);
        return BaseRespVo.ok(new PageVo<>(ads, total));
    }
}
