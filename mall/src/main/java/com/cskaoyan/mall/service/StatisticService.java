package com.cskaoyan.mall.service;

import java.util.Map;

/**
 * @author 赵亚云
 */
public interface StatisticService {
    Map<String,Object> userAddStatistic();
    Map<String,Object> orderDailyStatistic();
    Map<String,Object> goodsDailyStatistic();
}
