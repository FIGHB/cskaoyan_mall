package com.cskaoyan.mall.service;

import com.cskaoyan.mall.vo.ConfigExpressVo;
import com.cskaoyan.mall.vo.ConfigMallVo;
import com.cskaoyan.mall.vo.ConfigOrderVo;
import com.cskaoyan.mall.vo.ConfigWxVo;

/**
 * 配置管理
 * author：陈武
 */
public interface ConfigService {
    ConfigMallVo queryMall();

    ConfigExpressVo queryExpress();

    ConfigOrderVo queryOrder();

    ConfigWxVo queryWx();
}
