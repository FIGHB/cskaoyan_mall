package com.cskaoyan.mall.service.GuoService;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.GuoVo.GroupDetail;
import com.cskaoyan.mall.vo.GuoVo.GuoOrderGoods;
import com.cskaoyan.mall.vo.GuoVo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GuoGroupServiceImpl implements GuoGroupService {

    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    GuoSpecificationMapper guoSpecificationMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Override
    public GroupDetail getGroupDetailByGrouponId(Integer grouponId) {
        GroupDetail groupDetail = new GroupDetail();

        //groupon
        GrouponExample grouponExample = new GrouponExample();
        GrouponExample.Criteria criteria = grouponExample.createCriteria();
        criteria.andIdEqualTo(grouponId);
        List<Groupon> groupons = grouponMapper.selectByExample(grouponExample);
        Groupon groupon = groupons.get(0);
        groupDetail.setGroupon(groupon);

        //查询创建者
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria1 = userExample.createCriteria();
        criteria1.andIdEqualTo(groupon.getCreatorUserId());
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        groupDetail.setCreator(user);

        //joiners
        List<User> userList=new ArrayList<>();

        GrouponExample.Criteria criteria2 = grouponExample.createCriteria();
        criteria2.andGrouponIdEqualTo(groupon.getGrouponId());
        List<Groupon> groupons1 = grouponMapper.selectByExample(grouponExample);
        for (Groupon groupon1 : groupons1) {
            Integer userId = groupon1.getUserId();
            UserExample.Criteria criteria3 = userExample.createCriteria();
            criteria3.andIdEqualTo(userId);
            List<User> users1 = userMapper.selectByExample(userExample);
            User user1 = users1.get(0);
            userList.add(user1);
        }
        groupDetail.setJoiners(userList);

        //orderInfo
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria3 = orderExample.createCriteria();
        criteria3.andIdEqualTo(groupon.getOrderId());
        List<Order> orders = orderMapper.selectByExample(orderExample);
        Order order = orders.get(0);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setConsignee(order.getConsignee());
        orderInfo.setAddress(order.getAddress());
        orderInfo.setAddTime(order.getAddTime());
        orderInfo.setOrderSn(order.getOrderSn());
        orderInfo.setActualPrice(order.getActualPrice());
        orderInfo.setMobile(order.getMobile());
        HashMap status = getStatus();
        Short orderStatus = order.getOrderStatus();
        orderInfo.setOrderStatusText((String) status.get(orderStatus.toString()));
        orderInfo.setGoodsPrice(order.getGoodsPrice());
        orderInfo.setId(order.getId());
        orderInfo.setFreightPrice(order.getFreightPrice());
        groupDetail.setOrderInfo(orderInfo);

        //orderGoods
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        OrderGoodsExample.Criteria criteria4 = orderGoodsExample.createCriteria();
        criteria4.andOrderIdEqualTo(groupon.getOrderId());
        List<OrderGoods> orderGoods = orderGoodsMapper.selectByExample(orderGoodsExample);

        List<GuoOrderGoods> guoOrderGoodsList=new ArrayList<>();
        for (OrderGoods orderGood : orderGoods) {
            GuoOrderGoods guoOrderGoods = new GuoOrderGoods();
            guoOrderGoods.setNumber(orderGood.getNumber());
            guoOrderGoods.setPicUrl(orderGood.getPicUrl());
            guoOrderGoods.setOrderId(orderGood.getOrderId());
            guoOrderGoods.setGoodsId(orderGood.getGoodsId());
            List<GoodsSpecification> specificationByGoodsId = guoSpecificationMapper.getSpecificationByGoodsId(orderGood.getGoodsId());
            String[] goodsSpecificationList=new String[specificationByGoodsId.size()];
            int i=0;
            for (GoodsSpecification goodsSpecification : specificationByGoodsId) {
                String specification = goodsSpecification.getSpecification();
                goodsSpecificationList[i++]=specification;
            }
            guoOrderGoods.setGoodsSpecificationValues(goodsSpecificationList);
            guoOrderGoods.setId(orderGood.getId());
            guoOrderGoods.setGoodsName(orderGood.getGoodsName());
            guoOrderGoods.setRetailPrice(orderGood.getPrice());

            guoOrderGoodsList.add(guoOrderGoods);
        }
        groupDetail.setOrderGoods(guoOrderGoodsList);

        //rules
        GrouponRulesExample grouponRulesExample = new GrouponRulesExample();
        GrouponRulesExample.Criteria criteria5 = grouponRulesExample.createCriteria();
        criteria5.andGoodsIdEqualTo(groupDetail.getOrderGoods().get(0).getGoodsId());
        List<GrouponRules> grouponRules = grouponRulesMapper.selectByExample(grouponRulesExample);
        GrouponRules grouponRules1 = grouponRules.get(0);
        groupDetail.setRules(grouponRules1);

        //linkGroupponId 写死了
        groupDetail.setLinkGrouponId(1);

        return groupDetail;
    }
    public static HashMap getStatus() {
        HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
        stringStringHashMap.put("101", "未付款");
        stringStringHashMap.put("102", "用户取消");
        stringStringHashMap.put("103", "系统取消");
        stringStringHashMap.put("201", "已付款");
        stringStringHashMap.put("202", "申请付款");
        stringStringHashMap.put("203", "已退款");
        stringStringHashMap.put("301", "已发货");
        stringStringHashMap.put("401", "用户发货");
        stringStringHashMap.put("402", "系统发货");
        return stringStringHashMap;
    }
}
