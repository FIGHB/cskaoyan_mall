package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.wxfbean.StringCoupon;
import com.cskaoyan.mall.service.CouponService;
import com.cskaoyan.mall.utils.IsNumber;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
public class CouponController {

    @Autowired
    CouponService couponService;

    @RequestMapping("/admin/coupon/list")
    @RequiresPermissions("admin:coupon:list")
    public BaseRespVo couponList(int page, int limit, Coupon coupon){
        BaseRespVo baseRespVo = couponService.queryCouponByCondition(page,limit,coupon);
        return baseRespVo;
    }

    @RequestMapping("/admin/coupon/delete")
    @RequiresPermissions("admin:coupon:delete")
    public BaseRespVo delete(@RequestBody Coupon coupon){
        BaseRespVo delete = couponService.delete(coupon);
        return delete;
    }

    @RequestMapping("/admin/coupon/read")
    @RequiresPermissions("admin:coupon:read")
    public BaseRespVo read(int id){
        BaseRespVo read = couponService.read(id);
        return read;
    }

    @RequestMapping("/admin/coupon/listuser")
    @RequiresPermissions("admin:coupon:listuser")
    public BaseRespVo listuser(int page, int limit, CouponUser couponUser){
        BaseRespVo baseRespVo = couponService.queryCouponUserByCondition(page, limit, couponUser);
        return baseRespVo;
    }

    @RequestMapping("/admin/coupon/create")
    @RequiresPermissions("admin:coupon:create")
    public BaseRespVo create(@RequestBody HashMap hashMap) {
        if((int)hashMap.get("timeType")!=0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            try {
                String startTime1 =(String) hashMap.get("startTime");
                String endTime1 =(String) hashMap.get("endTime");
                if(startTime1==null||endTime1==null){
                    return BaseRespVo.fail(401, "优惠日期不能为空");
                }
                Date startTime = dateFormat.parse(startTime1);
                Date endTime = dateFormat.parse(endTime1);
                hashMap.put("startTime",startTime);
                hashMap.put("endTime",endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Object min1 = hashMap.get("min");
        Object limit1 = hashMap.get("limit");
        Object total1 = hashMap.get("total");
        Object discount1 = hashMap.get("discount");
        Object days1 = hashMap.get("days");
        if(min1 instanceof String) {
            String min = (String) min1;
            if(min!=null&&min!=""){
                if(min.length()>10){
                    return BaseRespVo.fail(401, "参数不对");
                }
                if(IsNumber.isNumber(min)) {
                    int i = Integer.parseInt(min);
                    BigDecimal bigDecimal = new BigDecimal(i);
                    hashMap.put("min",bigDecimal);
                }else {
                    return BaseRespVo.fail(401, "参数不对");
                }
            }
        }
        if(limit1 instanceof String){
            String limit = (String) limit1;
            if(limit!=null&&limit!=""){
                if(limit.length()>4){
                    return BaseRespVo.fail(401, "参数不对");
                }
                if(IsNumber.isNumber(limit)) {
                    short i = Short.parseShort(limit);
                    hashMap.put("limit",i);
                }else {
                    return BaseRespVo.fail(401, "参数不对");
                }
            }
        }
        if(total1 instanceof String){
            String total = (String) total1;
            if(total!=null&&total!=""){
                if(total.length()>10){
                    return BaseRespVo.fail(401, "参数不对");
                }
                if(IsNumber.isNumber(total)) {
                    int i = Integer.parseInt(total);
                    hashMap.put("total",i);
                }else {
                    return BaseRespVo.fail(401, "参数不对");
                }
            }
        }
        if(discount1 instanceof String){
            String discount = (String) discount1;
            if(discount!=null&&discount!=""){
                if(discount.length()>10){
                    return BaseRespVo.fail(401, "参数不对");
                }
                if(IsNumber.isNumber(discount)) {
                    int i = Integer.parseInt(discount);
                    BigDecimal bigDecimal = new BigDecimal(i);
                    hashMap.put("discount",bigDecimal);
                }else {
                    return BaseRespVo.fail(401, "参数不对");
                }
            }
        }
        if(days1 instanceof String){
            String days = (String) days1;
            if(days!=null&&days!=""){
                if(days.length()>4){
                    return BaseRespVo.fail(401, "参数不对");
                }
                if(IsNumber.isNumber(days)) {
                    short i = Short.parseShort(days);
                    hashMap.put("days",i);
                }else {
                    return BaseRespVo.fail(401, "参数不对");
                }
            }
        }

        Coupon coupon = new Coupon();
        try {
            BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BeanUtils.populate(coupon,hashMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        BaseRespVo insert = couponService.insert(coupon);
        return insert;
    }

}
