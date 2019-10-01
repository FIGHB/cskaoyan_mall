package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.vo.BaseRespVo;

public interface AdService {
    BaseRespVo queryAllAd(int start, int limit);
    BaseRespVo insert(Ad ad);

    BaseRespVo update(Ad ad);
}
