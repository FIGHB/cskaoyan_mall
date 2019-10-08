package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.cskaoyan.mall.mapper.WXMapper.WxfUserMapper;
import com.cskaoyan.mall.mapper.selfmapper.WxfCouponMapper;
import com.cskaoyan.mall.mapper.selfmapper.WxfCouponUserMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.PageVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    WxfCouponMapper wxfCouponMapper;
    @Autowired
    CouponMapper couponMapper;

    @Autowired
    WxfCouponUserMapper wxfCouponUserMapper;
    @Autowired
    WxfUserMapper wxfUserMapper;
    @Autowired
    CouponUserMapper couponUserMapper;
    @Override
    public BaseRespVo queryCouponByCondition(int page, int limit, Coupon coupon) {
        PageHelper.startPage(page,limit);
        Coupon[] coupons = wxfCouponMapper.queryCouponByCondition(coupon);
        long total = wxfCouponMapper.queryTotal(coupon);
        return BaseRespVo.ok(new PageVo<>(coupons, total));
    }

    @Override
    public BaseRespVo delete(Coupon coupon) {
        Coupon coupon1 = new Coupon();
        coupon1.setId(coupon.getId());
        coupon1.setDeleted(true);
        //couponMapper.deleteByPrimaryKey(coupon.getId());
        couponMapper.updateByPrimaryKeySelective(coupon1);
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
        if(coupon.getType()==2){
            Random random = new Random();
            while (true){
                int i = random.nextInt(1000000);
                String code=i+"";
                int total = wxfCouponMapper.queryCodeTotal(code);
                if(total==0){
                    coupon.setCode(code);
                    break;
                }
            }

        }
        wxfCouponMapper.insertSelective(coupon);
        Coupon couponResp = wxfCouponMapper.selectLastInsert();
        return BaseRespVo.ok(couponResp);
    }

    @Override
    public BaseRespVo exchange(Coupon coupon,String username) {
        int i = wxfCouponMapper.queryCodeTotal(coupon.getCode());//查询优惠是否存在
        if(i!=0){
            Coupon couponResp = wxfCouponMapper.queryCouponByCode(coupon.getCode());
            Short limit = couponResp.getLimit();
            int userId = wxfUserMapper.queryUserId(username);
            CouponUser couponUser = new CouponUser();
            couponUser.setCouponId(couponResp.getId());
            couponUser.setUserId(userId);
            CouponUser[] couponUsers = wxfCouponUserMapper.queryCouponUserByCondition(couponUser);
            if(couponUsers.length<limit){
                Date date = new Date();
                couponUser.setAddTime(date);
                couponUser.setUpdateTime(date);
                couponUser.setStartTime(couponResp.getStartTime());
                couponUser.setEndTime(couponResp.getEndTime());
                couponUserMapper.insertSelective(couponUser);
                //插入数据到cakaoyan_mall_coupon_user
            }else {
                return BaseRespVo.fail(741,"超出限领数量");
            }
        }else {
            return BaseRespVo.fail(741,"优惠券不存在");
        }
        return BaseRespVo.ok(null);
    }
}
