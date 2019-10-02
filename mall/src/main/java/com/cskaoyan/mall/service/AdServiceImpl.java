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
    public BaseRespVo queryAllAd(int start, int limit) {
        PageHelper.startPage(start,limit);
        Ad[] ads = wxfAdMapper.selectAllAd();
        long total = wxfAdMapper.queryTotal();
        return BaseRespVo.ok(new PageVo<>(ads, total));
    }

    @Override
    @Transactional
    public BaseRespVo insert(Ad ad) {
        Date date = new Date();
        ad.setAddTime(date);
        ad.setUpdateTime(date);
        adMapper.insertSelective(ad);
        //Ad adResp = adMapper.selectByContent(ad.getContent());
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(ad.getAddTime());*/
        //Ad adResp = wxfAdMapper.selectByAddTime(ad.getAddTime());
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
        Ad[] ads = wxfAdMapper.queryByNameAndContent("%"+ad.getName()+"%","%"+ad.getContent()+"%");
        PageInfo<Ad> adPageInfo = new PageInfo<>(Arrays.asList(ads));
        long total = adPageInfo.getTotal();
        return BaseRespVo.ok(new PageVo<>(ads, total));
    }
}
