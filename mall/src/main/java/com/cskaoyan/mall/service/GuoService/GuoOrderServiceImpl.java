package com.cskaoyan.mall.service.GuoService;

import com.cskaoyan.mall.mapper.GuoMapper.GuoOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuoOrderServiceImpl implements GuoOrderService {

    @Autowired
    GuoOrderMapper guoOrderMapper;

    @Override
    public Integer cancelOrderById(Integer orderId) {
        int i = guoOrderMapper.updateOrderDeletedById(orderId);
        return i;
    }
}
