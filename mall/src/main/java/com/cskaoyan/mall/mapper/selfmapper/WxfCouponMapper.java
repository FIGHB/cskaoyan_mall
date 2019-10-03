package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.Coupon;
import org.apache.ibatis.annotations.Param;

public interface WxfCouponMapper {
    Coupon[] selectAllCoupon();

    long queryTotal(Coupon coupon);

    Coupon selectByPrimaryKey(Integer id);

    int insertSelective(Coupon record);

    Coupon selectLastInsert();

    Coupon[] queryCouponByCondition(Coupon coupon);
}
