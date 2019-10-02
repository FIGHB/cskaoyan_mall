package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.service.CouponService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping("/admin/coupon/list")
    public BaseRespVo couponList(int page, int limit, Coupon coupon){
        BaseRespVo baseRespVo = null;
        if(coupon.getName()==null){
            baseRespVo = couponService.queryAllCoupon(page, limit);
        }else {
            baseRespVo = couponService.queryCouponByCondition(page,limit,coupon);
        }
        return baseRespVo;
    }

    @RequestMapping("/admin/coupon/delete")
    public BaseRespVo delete(Coupon coupon){
        BaseRespVo delete = couponService.delete(coupon);
        return delete;
    }

    @RequestMapping("/admin/coupon/read")
    public BaseRespVo read(int id){
        BaseRespVo read = couponService.read(id);
        return read;
    }

    @RequestMapping("/admin/coupon/listuser")
    public BaseRespVo listuser(int page, int limit, CouponUser couponUser){
        BaseRespVo baseRespVo = couponService.queryCouponUserByCondition(page, limit, couponUser);
        return baseRespVo;
    }

    @RequestMapping("/admin/coupon/create")
    public BaseRespVo create(Coupon coupon){
        BaseRespVo insert = couponService.insert(coupon);
        return insert;
    }

}
