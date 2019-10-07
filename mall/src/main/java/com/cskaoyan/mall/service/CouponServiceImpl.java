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
    public BaseRespVo queryCouponByCondition(int page, int limit, Coupon coupon) {
        PageHelper.startPage(page,limit);
        Coupon[] coupons = wxfCouponMapper.queryCouponByCondition(coupon);
        long total = wxfCouponMapper.queryTotal(coupon);
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
        long total = wxfCouponUserMapper.queryTotal(couponUser);
        return BaseRespVo.ok(new PageVo<>(couponUsers, total));
    }

    @Override
    public BaseRespVo insert(Coupon coupon) {
        if((coupon.getTotal()!=null&&coupon.getTotal()<0)||
                (coupon.getLimit()!=null&&coupon.getTotal()<0) ||
                (coupon.getMin()!=null&&coupon.getMin().signum()==-1)||
                (coupon.getDiscount()!=null&&coupon.getDiscount().signum()==-1)){
            return BaseRespVo.fail(500,"参数不对");
        }
        if(coupon.getTimeType()==0){
            if(coupon.getDays()==null){
                return BaseRespVo.fail(500,"优惠天数不能为空");
            }else {
                if(coupon.getDays()<0){
                    return BaseRespVo.fail(500,"优惠天数不能为负数");
                }
            }
        }else if(coupon.getTimeType()==1){
            if(coupon.getStartTime()==null||coupon.getEndTime()==null){
                return BaseRespVo.fail(500,"优惠日期不能为空");
            }
        }else {
            return BaseRespVo.fail(500,"参数不对");
        }

        wxfCouponMapper.insertSelective(coupon);
        Coupon couponResp = wxfCouponMapper.selectLastInsert();
        return BaseRespVo.ok(couponResp);
    }
}
