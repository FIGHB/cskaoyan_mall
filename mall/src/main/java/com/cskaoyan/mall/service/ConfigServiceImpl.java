package com.cskaoyan.mall.service;

import com.cskaoyan.mall.mapper.SystemMapper;
import com.cskaoyan.mall.vo.ConfigExpressVo;
import com.cskaoyan.mall.vo.ConfigMallVo;
import com.cskaoyan.mall.vo.ConfigOrderVo;
import com.cskaoyan.mall.vo.ConfigWxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 配置管理
 * author：陈武
 */
@Service
public class ConfigServiceImpl implements ConfigService{
    @Autowired
    SystemMapper systemMapper;
    @Override
    public ConfigMallVo queryMall() {
        ConfigMallVo queryVo = new ConfigMallVo();
        queryVo.setCskaoyan_mall_mall_address(systemMapper.queryMall("cskaoyan_mall_mall_address"));
        queryVo.setCskaoyan_mall_mall_name(systemMapper.queryMall("cskaoyan_mall_mall_name"));
        queryVo.setCskaoyan_mall_mall_phone(systemMapper.queryMall("cskaoyan_mall_mall_phone"));
        queryVo.setCskaoyan_mall_mall_qq(systemMapper.queryMall("cskaoyan_mall_mall_qq"));
        return queryVo;
    }

    @Override
    public ConfigExpressVo queryExpress() {
        ConfigExpressVo configExpressVo = new ConfigExpressVo();
        configExpressVo.setCskaoyan_mall_express_freight_min(systemMapper.queryExpress("cskaoyan_mall_express_freight_min"));
        configExpressVo.setCskaoyan_mall_express_freight_value(systemMapper.queryExpress("cskaoyan_mall_express_freight_value"));
        return configExpressVo;
    }

    @Override
    public ConfigOrderVo queryOrder() {
        ConfigOrderVo configOrderVo = new ConfigOrderVo();
       configOrderVo.setCskaoyan_mall_order_comment(systemMapper.queryOrder("cskaoyan_mall_order_comment"));
        configOrderVo.setCskaoyan_mall_order_unconfirm(systemMapper.queryOrder("cskaoyan_mall_order_unconfirm"));
        configOrderVo.setCskaoyan_mall_order_unpaid(systemMapper.queryOrder("cskaoyan_mall_order_unpaid"));
        return configOrderVo;
    }

    @Override
    public ConfigWxVo queryWx() {
        ConfigWxVo configWxVo = new ConfigWxVo();
       configWxVo.setCskaoyan_mall_wx_index_new(systemMapper.queryWx("cskaoyan_mall_wx_index_new"));
       configWxVo.setCskaoyan_mall_wx_catlog_goods(systemMapper.queryWx("cskaoyan_mall_wx_catlog_goods"));
       configWxVo.setCskaoyan_mall_wx_catlog_list(systemMapper.queryWx("cskaoyan_mall_wx_catlog_list"));
       configWxVo.setCskaoyan_mall_wx_share(systemMapper.queryWx("cskaoyan_mall_wx_share"));
       configWxVo.setCskaoyan_mall_wx_index_brand(systemMapper.queryWx("cskaoyan_mall_wx_index_brand"));
       configWxVo.setCskaoyan_mall_wx_index_hot(systemMapper.queryWx("cskaoyan_mall_wx_index_hot"));
       configWxVo.setCskaoyan_mall_wx_index_topic(systemMapper.queryWx("cskaoyan_mall_wx_index_topic"));
        return configWxVo;
    }
}
