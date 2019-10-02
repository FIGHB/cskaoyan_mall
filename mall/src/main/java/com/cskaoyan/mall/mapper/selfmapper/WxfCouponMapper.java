package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.Coupon;
import org.apache.ibatis.annotations.Param;

public interface WxfCouponMapper {
    Coupon[] selectAllCoupon();

    long queryTotal();

    Coupon selectByPrimaryKey(Integer id);

    int insertSelective(Coupon record);

    Coupon selectLastInsert();

    Coupon[] queryCouponByCondition(@Param("name") String name,@Param("coupon") Coupon coupon);
}
