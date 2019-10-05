package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.ConfigService;
import com.cskaoyan.mall.vo.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用于配置管理
 * author：陈武
 */
@RestController
@RequestMapping("admin/config/")
public class ConfigController {
    @Autowired
    ConfigService configService;

    /**
     * @return 商场配置
     */
    @RequestMapping("mall")
    public  BaseRespVo<ConfigMallVo> mall() {
        ConfigMallVo queryVo = configService.queryMall();
        BaseRespVo baseRespVo = BaseRespVo.ok(queryVo);
        return baseRespVo;
    }

    @PostMapping("mall")
    public  BaseRespVo<ConfigMallVo>mallPost(@RequestBody ConfigMallVo configMallVo) {
        configService.addMall(configMallVo);
        BaseRespVo baseRespVo = BaseRespVo.ok(configMallVo);
        return baseRespVo;
    }

    /**
     * @return 运费配置
     */
    @GetMapping("express")
    public  BaseRespVo<ConfigExpressVo> expressGet() {
        ConfigExpressVo configExpressVo = configService.queryExpress();
        BaseRespVo baseRespVo = BaseRespVo.ok(configExpressVo);
        return baseRespVo;
    }

    @PostMapping("express")
    public  BaseRespVo<ConfigExpressVo> expressPost(@RequestBody ConfigExpressVo configExpressVo) {
        /*将数据改入数据库*/
        configService.addExpress(configExpressVo);
        BaseRespVo baseRespVo = BaseRespVo.ok(configExpressVo);
        return baseRespVo;
    }


    /**
     * @return 订单配置
     */
    @GetMapping("order")
    public  BaseRespVo<ConfigOrderVo> orderGet() {
        ConfigOrderVo configOrderVo = configService.queryOrder();
        BaseRespVo baseRespVo = BaseRespVo.ok(configOrderVo);
        return baseRespVo;
    }

    @PostMapping("order")
    public  BaseRespVo<ConfigOrderVo>mallPost(@RequestBody ConfigOrderVo configOrderVo) {
        configService.addOrder(configOrderVo);
        BaseRespVo baseRespVo = BaseRespVo.ok(configOrderVo);
        return baseRespVo;
    }

    /**
     * @return 小程序配置
     */
    @GetMapping("wx")
    public  BaseRespVo<ConfigWxVo> wxGet() {
        ConfigWxVo configWxVo = configService.queryWx();
        BaseRespVo baseRespVo = BaseRespVo.ok(configWxVo);
        return baseRespVo;
    }

    @PostMapping("wx")
    public  BaseRespVo<ConfigWxVo> mallPost(@RequestBody ConfigWxVo configWxVo) {
        configService.addWx(configWxVo);
        BaseRespVo baseRespVo = BaseRespVo.ok(configWxVo);
        return baseRespVo;
    }
}
