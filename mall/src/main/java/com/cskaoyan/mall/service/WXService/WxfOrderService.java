package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.vo.BaseRespVo;

public interface WxfOrderService {
    BaseRespVo delete(OrderGoods orderGoods);
}
