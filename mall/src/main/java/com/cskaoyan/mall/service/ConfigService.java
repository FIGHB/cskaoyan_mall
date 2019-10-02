package com.cskaoyan.mall.service;

import com.cskaoyan.mall.vo.ConfigExpressVo;
import com.cskaoyan.mall.vo.ConfigMallVo;
import com.cskaoyan.mall.vo.ConfigOrderVo;
import com.cskaoyan.mall.vo.ConfigWxVo;
import org.apache.ibatis.annotations.Param;

/**
 * 配置管理
 * author：陈武
 */
public interface ConfigService {
    ConfigMallVo queryMall();

    void addMall(ConfigMallVo configMallVo);

    ConfigExpressVo queryExpress();

    void addExpress(ConfigExpressVo configExpressVo);

    ConfigOrderVo queryOrder();

    void addOrder(ConfigOrderVo configOrderlVo);

    ConfigWxVo queryWx();

    void addWx(ConfigWxVo configWxVo);

}
