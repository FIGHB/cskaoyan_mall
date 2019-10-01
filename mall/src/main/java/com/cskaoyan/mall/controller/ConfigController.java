package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.ConfigService;
import com.cskaoyan.mall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        BaseRespVo<ConfigMallVo> queryVoBaseRespVo = new BaseRespVo<>();
        queryVoBaseRespVo.setData(queryVo);
        queryVoBaseRespVo.setErrmsg("成功");
        queryVoBaseRespVo.setErrno(0);
        return queryVoBaseRespVo;
    }

    /**
     * @return 运费配置
     */
    @RequestMapping("express")
    public  BaseRespVo<ConfigExpressVo> express() {
        ConfigExpressVo configExpressVo = configService.queryExpress();
        BaseRespVo<ConfigExpressVo> configExpressVoBaseRespVo = new BaseRespVo<>();
        configExpressVoBaseRespVo.setData(configExpressVo);
        configExpressVoBaseRespVo.setErrmsg("成功");
        configExpressVoBaseRespVo.setErrno(0);
        return configExpressVoBaseRespVo;
    }


    /**
     * @return 订单配置
     */
    @RequestMapping("order")
    public  BaseRespVo<ConfigOrderVo> order() {
        ConfigOrderVo configOrderVo = configService.queryOrder();
        BaseRespVo<ConfigOrderVo> configOrderVoBaseRespVo = new BaseRespVo<>();
        configOrderVoBaseRespVo.setData(configOrderVo);
        configOrderVoBaseRespVo.setErrmsg("成功");
        configOrderVoBaseRespVo.setErrno(0);
        return configOrderVoBaseRespVo;
    }

    /**
     * @return 小程序配置
     */
    @RequestMapping("wx")
    public  BaseRespVo<ConfigWxVo> wx() {
        ConfigWxVo configWxVo = configService.queryWx();
        BaseRespVo<ConfigWxVo> configWxVoBaseRespVo = new BaseRespVo<>();
       configWxVoBaseRespVo.setData(configWxVo);
       configWxVoBaseRespVo.setErrmsg("成功");
       configWxVoBaseRespVo.setErrno(0);
        return configWxVoBaseRespVo;
    }

}
