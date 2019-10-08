package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.mapper.WXMapper.WxfOrderMapper;
import com.cskaoyan.mall.mapper.WXMapper.WxfProductMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WxfOrderServiceImpl implements WxfOrderService{
    @Autowired
    WxfOrderMapper wxfOrderMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    WxfProductMapper wxfProductMapper;
    @Transactional
    @Override
    public BaseRespVo delete(OrderGoods orderGoods) {

        List<Integer> integers = wxfOrderMapper.queryproductId(orderGoods);
        int size = integers.size();
        for (int i = 0; i <size; i++) {
            wxfProductMapper.updateStorage(integers.get(i));
        }
        wxfOrderMapper.deleteByOrderId(orderGoods);
        wxfOrderMapper.deleteById(orderGoods);

        return BaseRespVo.ok(null);
    }

}
