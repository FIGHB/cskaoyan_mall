package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdMapper adMapper;
    @Override
    public BaseRespVo queryAllAd(int start, int limit) {
        PageHelper.startPage(start,limit);
        Ad[] ads = adMapper.selectAllAd();
        PageInfo<Ad> adPageInfo = new PageInfo<>();
        long total = adPageInfo.getTotal();
        return BaseRespVo.ok(new PageVo<>(ads, total));
    }

    @Override
    public BaseRespVo insert(Ad ad) {
        adMapper.insertSelective(ad);
        Ad adResp = adMapper.selectByContent(ad.getContent());
        return BaseRespVo.ok(adResp);
    }

    @Override
    public BaseRespVo update(Ad ad) {
        adMapper.updateByPrimaryKeySelective(ad);
        Ad adResp = adMapper.selectByPrimaryKey(ad.getId());
        return BaseRespVo.ok(adResp);
    }
}
