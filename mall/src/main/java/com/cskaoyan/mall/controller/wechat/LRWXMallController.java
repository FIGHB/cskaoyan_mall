package com.cskaoyan.mall.controller.wechat;

import com.cskaoyan.mall.service.wechat.LRWXMallService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author 李锐
 */
@RestController
@RequestMapping("/wx")
public class LRWXMallController {

    @Autowired
    LRWXMallService lrwxMallService;

    /**
     * 小程序部分，点击分类显示的内容
     * @return
     */
    @RequestMapping("/catalog/index")
    public BaseRespVo queryCatalogIndex() {
        return BaseRespVo.ok(lrwxMallService.queryCatalogIndex());
    }

    /**
     * 搜索框内的接口
     * @return
     */
    @RequestMapping("/goods/count")
    public BaseRespVo queryGoodsCount() {
        return BaseRespVo.ok(lrwxMallService.queryGoodsCount());
    }


    @GetMapping("/search/index")
    public BaseRespVo querySearchIndex() {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        String username = (String) principal;
        return BaseRespVo.ok(lrwxMallService.querySearchIndex(username));
    }


    /**
     * 在搜索框内输入内容时提示的信息
     * @param keyword
     * @return
     */
    @GetMapping("/search/helper")
    public BaseRespVo querySearchHelper(String keyword) {
        return BaseRespVo.ok(lrwxMallService.querySearchHelper(keyword));
    }


    /**
     * 清除搜索历史
     * @return
     */
    @PostMapping("/search/clearhistory")
    public BaseRespVo deleteSearchHistory() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        boolean flag = lrwxMallService.deleteSearchHistory(username);
        if(flag) {
            return BaseRespVo.ok("成功");
        } else {
            return BaseRespVo.getBaseResVo(500, null, "删除失败");
        }
    }


    /**
     * 登录后显示的和各订单总数相关的接口
     * @return
     */
    @GetMapping("/user/index")
    public BaseRespVo queryUserIndex() {
        String username = getUsernameByShiro();
        return BaseRespVo.ok(lrwxMallService.queryUserIndex(username));
    }

    private String getUsernameByShiro() {
        Subject subject = SecurityUtils.getSubject();
        return (String) subject.getPrincipal();
    }

    /**
     * 登陆后点击优惠券显示的内容
     * @param status
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/coupon/mylist")
    public BaseRespVo queryMyCouponList(@Param("status")int status,
                                        @Param("page")int page,@Param("size")int size) {
        String username = getUsernameByShiro();

        return BaseRespVo.ok(lrwxMallService.queryMyCouponList(username, status, page, size));
    }

    @PostMapping("/coupon/receive")
    public BaseRespVo receiveCoupon(@RequestBody Map map) {
        Integer couponId = (Integer)map.get("couponId");
        String username = getUsernameByShiro();
        if(username == null) return BaseRespVo.getBaseResVo(501, null, "请登录");
        //如果有错误消息则返回失败
        String errmsg = lrwxMallService.receiveCoupon(username, couponId);
        if(errmsg != null) {
            return BaseRespVo.getBaseResVo(740, null, errmsg);
        }
        return BaseRespVo.ok("成功");
    }

    //点击首页优惠券发送的请求
    @GetMapping("/coupon/list")
    public BaseRespVo queryCouponList(@Param("page") int page, @Param("size")int size) {
        return BaseRespVo.ok(lrwxMallService.queryCouponList(page, size));
    }

    //返回购物车总数
    @GetMapping("/cart/goodscount")
    public BaseRespVo getGoodsCount() {
        String username = getUsernameByShiro();
        return BaseRespVo.ok(lrwxMallService.getGoodsCount(username));

    }
}
