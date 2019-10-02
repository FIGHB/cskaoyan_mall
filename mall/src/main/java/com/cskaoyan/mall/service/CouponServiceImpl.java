package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.selfmapper.WxfCouponMapper;
import com.cskaoyan.mall.mapper.selfmapper.WxfCouponUserMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    WxfCouponMapper wxfCouponMapper;
    @Autowired
    CouponMapper couponMapper;

    @Autowired
    WxfCouponUserMapper wxfCouponUserMapper;
    @Override
    public BaseRespVo queryAllCoupon(int start, int limit) {
        PageHelper.startPage(start,limit);
        Coupon[] coupons = wxfCouponMapper.selectAllCoupon();
        long total = wxfCouponMapper.queryTotal();
        return BaseRespVo.ok(new PageVo<>(coupons, total));
    }

    @Override
    public BaseRespVo queryCouponByCondition(int page, int limit, Coupon coupon) {

        PageHelper.startPage(page,limit);
        Coupon[] coupons = wxfCouponMapper.queryCouponByCondition("%"+coupon.getName()+"%", coupon);
        PageInfo<Coupon> pageInfo = new PageInfo<Coupon>(Arrays.asList(coupons));
        long total = pageInfo.getTotal();
        return BaseRespVo.ok(new PageVo<>(coupons, total));
    }

    @Override
    public BaseRespVo delete(Coupon coupon) {
        couponMapper.deleteByPrimaryKey(coupon.getId());
        return BaseRespVo.ok(null);
    }

    @Override
    public BaseRespVo read(int id) {
        Coupon coupon = wxfCouponMapper.selectByPrimaryKey(id);
        return BaseRespVo.ok(coupon);
    }

    @Override
    public BaseRespVo queryCouponUser(int page, int limit,int couponId) {
        PageHelper.startPage(page,limit);
        CouponUser[] couponUsers = wxfCouponUserMapper.selectByCouponId(couponId);
        PageInfo<CouponUser> pageInfo = new PageInfo<CouponUser>(Arrays.asList(couponUsers));
        long total = pageInfo.getTotal();
        return BaseRespVo.ok(new PageVo<>(couponUsers, total));
    }

    @Override
    public BaseRespVo queryCouponUserByCondition(int page, int limit,CouponUser couponUser) {
        PageHelper.startPage(page,limit);
        CouponUser[] couponUsers = wxfCouponUserMapper.queryCouponUserByCondition(couponUser);
        PageInfo<CouponUser> tPageInfo = new PageInfo<>(Arrays.asList(couponUsers));
        long total = tPageInfo.getTotal();
        return BaseRespVo.ok(new PageVo<>(couponUsers, total));
    }

    @Override
    public BaseRespVo insert(Coupon coupon) {
        wxfCouponMapper.insertSelective(coupon);
        Coupon couponResp = wxfCouponMapper.selectLastInsert();
        return BaseRespVo.ok(couponResp);
    }
}
