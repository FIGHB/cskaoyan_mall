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
    public void addMall(ConfigMallVo configMallVo) {
        String cskaoyan_mall_mall_address = configMallVo.getCskaoyan_mall_mall_address();
        String cskaoyan_mall_mall_name = configMallVo.getCskaoyan_mall_mall_name();
        String cskaoyan_mall_mall_phone = configMallVo.getCskaoyan_mall_mall_phone();
        String cskaoyan_mall_mall_qq = configMallVo.getCskaoyan_mall_mall_qq();
        systemMapper.addMall(cskaoyan_mall_mall_address,"cskaoyan_mall_mall_address");
        systemMapper.addMall(cskaoyan_mall_mall_name,"cskaoyan_mall_mall_name");
        systemMapper.addMall(cskaoyan_mall_mall_phone,"cskaoyan_mall_mall_phone");
        systemMapper.addMall(cskaoyan_mall_mall_qq,"cskaoyan_mall_mall_qq");
    }

    @Override
    public ConfigExpressVo queryExpress() {
        ConfigExpressVo configExpressVo = new ConfigExpressVo();
        configExpressVo.setCskaoyan_mall_express_freight_min(systemMapper.queryExpress("cskaoyan_mall_express_freight_min"));
        configExpressVo.setCskaoyan_mall_express_freight_value(systemMapper.queryExpress("cskaoyan_mall_express_freight_value"));
        return configExpressVo;
    }

    @Override
    public void addExpress(ConfigExpressVo configExpressVo) {
        String cskaoyan_mall_express_freight_min = configExpressVo.getCskaoyan_mall_express_freight_min();
        String cskaoyan_mall_express_freight_value = configExpressVo.getCskaoyan_mall_express_freight_value();
        systemMapper.addExpress(cskaoyan_mall_express_freight_min,"cskaoyan_mall_express_freight_min");
        systemMapper.addExpress(cskaoyan_mall_express_freight_value,"cskaoyan_mall_express_freight_value");
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
    public void addOrder(ConfigOrderVo configOrderlVo) {
        String cskaoyan_mall_order_comment = configOrderlVo.getCskaoyan_mall_order_comment();
        String cskaoyan_mall_order_unconfirm = configOrderlVo.getCskaoyan_mall_order_unconfirm();
        String cskaoyan_mall_order_unpaid = configOrderlVo.getCskaoyan_mall_order_unpaid();
        systemMapper.addOrder(cskaoyan_mall_order_comment,"cskaoyan_mall_order_comment");
        systemMapper.addOrder(cskaoyan_mall_order_unconfirm,"cskaoyan_mall_order_unconfirm");
        systemMapper.addOrder(cskaoyan_mall_order_unpaid,"cskaoyan_mall_order_unpaid");
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

    @Override
    public void addWx(ConfigWxVo configWxVo) {
        String cskaoyan_mall_wx_catlog_goods = configWxVo.getCskaoyan_mall_wx_catlog_goods();
        String cskaoyan_mall_wx_catlog_list = configWxVo.getCskaoyan_mall_wx_catlog_list();
        String cskaoyan_mall_wx_index_brand = configWxVo.getCskaoyan_mall_wx_index_brand();
        String cskaoyan_mall_wx_index_hot = configWxVo.getCskaoyan_mall_wx_index_hot();
        String cskaoyan_mall_wx_index_new = configWxVo.getCskaoyan_mall_wx_index_new();
        String cskaoyan_mall_wx_index_topic = configWxVo.getCskaoyan_mall_wx_index_topic();
        String cskaoyan_mall_wx_share = configWxVo.getCskaoyan_mall_wx_share();
        systemMapper.addOrder(cskaoyan_mall_wx_catlog_goods,"cskaoyan_mall_wx_catlog_goods");
        systemMapper.addOrder(cskaoyan_mall_wx_catlog_list,"cskaoyan_mall_wx_catlog_list");
        systemMapper.addOrder(cskaoyan_mall_wx_index_brand,"cskaoyan_mall_wx_index_brand");
        systemMapper.addOrder(cskaoyan_mall_wx_index_hot,"cskaoyan_mall_wx_index_hot");
        systemMapper.addOrder(cskaoyan_mall_wx_index_new,"cskaoyan_mall_wx_index_new");
        systemMapper.addOrder(cskaoyan_mall_wx_index_topic,"cskaoyan_mall_wx_index_topic");
        systemMapper.addOrder(cskaoyan_mall_wx_share,"cskaoyan_mall_wx_share");
    }
}
