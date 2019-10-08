package com.cskaoyan.mall.service.wechat;

import com.cskaoyan.mall.mapper.wechat.OrderDeleteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDeleteServiceImpl implements OrderDeleteService {
    @Autowired
    OrderDeleteMapper orderDeleteMapper;
    @Override
    public boolean orderDelete(int id) {
        int i = orderDeleteMapper.updateStateOfDelete(id,false);
        if (i>0){return true;}
        return false;
    }
}
