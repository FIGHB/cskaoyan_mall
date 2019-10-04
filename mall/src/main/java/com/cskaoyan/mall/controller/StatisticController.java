package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.StatisticService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 赵亚云
 */

@RestController
public class StatisticController {
    @Autowired
    StatisticService statisticService;

    @RequestMapping("admin/stat/user")
    public BaseRespVo userStatistic(){
        Map<String, Object> data = statisticService.userAddStatistic();
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }

    @RequestMapping("admin/stat/order")
    public  BaseRespVo orderStatistic(){
        Map<String, Object> data = statisticService.orderDailyStatistic();
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }

    @RequestMapping("admin/stat/goods")
    public  BaseRespVo goodsStatistic(){
        Map<String, Object> data = statisticService.goodsDailyStatistic();
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }
}

