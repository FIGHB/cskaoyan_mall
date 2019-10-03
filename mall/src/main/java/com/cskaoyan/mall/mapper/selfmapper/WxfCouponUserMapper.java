package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.CouponUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxfCouponUserMapper {
    CouponUser[] selectByCouponId(@Param("coupon_id") int couponId);

    CouponUser[] queryCouponUserByCondition(CouponUser couponUser);

    long queryTotal(CouponUser couponUser);
}
