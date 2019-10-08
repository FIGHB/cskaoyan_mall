package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.vo.BaseRespVo;

import java.util.HashMap;

public interface CouponService {

    BaseRespVo queryCouponByCondition(int page, int limit, Coupon coupon);

    BaseRespVo delete(Coupon coupon);

    BaseRespVo read(int id);

    BaseRespVo queryCouponUser(int page, int limit,int couponId);
    BaseRespVo queryCouponUserByCondition(int page, int limit,CouponUser couponUser);

    BaseRespVo insert(Coupon coupon);


    BaseRespVo exchange(Coupon coupon,String username);
}
