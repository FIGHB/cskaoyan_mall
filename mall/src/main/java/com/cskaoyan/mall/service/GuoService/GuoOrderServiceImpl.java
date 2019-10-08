package com.cskaoyan.mall.service.GuoService;

import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.OrderGoodsExample;
import com.cskaoyan.mall.mapper.GuoMapper.GuoGoodsProductMapper;
import com.cskaoyan.mall.mapper.GuoMapper.GuoOrderMapper;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuoOrderServiceImpl implements GuoOrderService {

    @Autowired
    GuoOrderMapper guoOrderMapper;
    @Autowired
    GuoGoodsProductMapper guogoodsProductMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Override
    @Transactional
    public Integer cancelOrderById(Integer orderId) {
        int i = guoOrderMapper.updateOrderDeletedById(orderId);
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        OrderGoodsExample.Criteria criteria = orderGoodsExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);
        for (OrderGoods orderGood : orderGoods) {
            Integer productId = orderGood.getProductId();
            Short number = orderGood.getNumber();
            int pnumber=guogoodsProductMapper.getProductNumberById(productId);
            int numberd=pnumber-number;
            int updateNumberByProductId=guogoodsProductMapper.updateNumberByProductId(productId,numberd);
        }
        return i;
    }
}
