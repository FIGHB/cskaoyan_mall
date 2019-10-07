package com.cskaoyan.mall.controller.WXController;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.mapper.selfmapper.WxfCouponMapper;
import com.cskaoyan.mall.service.CouponService;
import com.cskaoyan.mall.utils.GetUserName;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class CouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping("/wx/coupon/exchange")
    public BaseRespVo exchange(@RequestBody Coupon coupon){
        String userName = GetUserName.getUserName();
        if(userName==null){
            return BaseRespVo.fail(501,"请登录");
        }
        BaseRespVo baseRespVo = couponService.exchange(coupon,userName);
        return baseRespVo;
    }

}
