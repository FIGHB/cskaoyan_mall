package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.GoodsStatistic;
import com.cskaoyan.mall.bean.OrderStatistic;
import com.cskaoyan.mall.bean.UserStatistic;
import com.cskaoyan.mall.mapper.StatisticMapper;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 赵亚云
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    StatisticMapper statisticMapper;
    @Override
    public Map<String, Object> userAddStatistic() {
        List<UserStatistic> userStatisticsList = statisticMapper.userAddStatistic();
        List<String> columnList = new ArrayList<>();
        columnList.add("day");
        columnList.add("users");
        Map<String,Object> statisticMap = new HashMap<>();
       statisticMap.put("columns",columnList);
       statisticMap.put("rows",userStatisticsList);
       return statisticMap;
    }

    @Override
    public Map<String, Object> orderDailyStatistic() {
        //时间
        List<Date> day = statisticMapper.selectOrderDay();
        //总价查询
        List<Double> amountList = statisticMapper.selectOrderAmount();
        //订单条数
        List<Integer> orderItems = statisticMapper.selectOrderItems();
        //用户条数 customers
        List<Integer> userItems = statisticMapper.selectUserItems();
        List<OrderStatistic> orderStatisticList = new ArrayList<>();
        OrderStatistic orderStatistic ;
        //{amount: 9622, orders: 8, customers: 1, day: "2019-08-20", pcr: 9622}
        for (int i = 0;i<day.size();i++){
            orderStatistic = new OrderStatistic();
            //总价
            double amount = amountList.get(i);
            orderStatistic.setAmount(amount);
            //订单条数
            orderStatistic.setOrders(orderItems.get(i));
            //用户数
            int customers = userItems.get(i);
            orderStatistic.setCustomers(customers);
            //日期
            orderStatistic.setDay(day.get(i));
            //人均价
            double pcr = amount/customers;
            orderStatistic.setPcr(pcr);
            orderStatisticList.add(orderStatistic);
        }

        ArrayList<String> columnList = new ArrayList<>();
        //columns: ["day", "orders", "customers", "amount", "pcr"]
        columnList.add("day");
        columnList.add("orders");
        columnList.add("customers");
        columnList.add("amount");
        columnList.add("pcr");
        HashMap<String, Object> orderStatisticHashmap = new HashMap<>();
        orderStatisticHashmap.put("columns",columnList);
        orderStatisticHashmap.put("rows",orderStatisticList);
        return orderStatisticHashmap;
    }

    @Override
    public Map<String, Object> goodsDailyStatistic() {
        //总价 amount
        List<Double> amountList = statisticMapper.selectGoodsAmount();
        //订单条数 orders
        List<Integer> orderItems = statisticMapper.selectGoodsOrderItems();
        //时间 day
        List<Date> day = statisticMapper.selectGoodsDay();
        //产品数量
        List<Integer> productsList = statisticMapper.selectGoodsNum();
        List<GoodsStatistic> goodsStatisticList = new ArrayList<>();
        GoodsStatistic goodsStatisticObject;
        for (int i = 0; i < day.size(); i++) {
            goodsStatisticObject = new GoodsStatistic();
            //amount: 65017, orders: 26, day: "2019-08-20", products: 152}
            goodsStatisticObject.setAmount(amountList.get(i));
            goodsStatisticObject.setOrders(orderItems.get(i));
            goodsStatisticObject.setDay(day.get(i));
            int products = productsList.get(i);
            goodsStatisticObject.setProducts(products);
            goodsStatisticList.add(goodsStatisticObject);
        }
        ArrayList<String> columnList = new ArrayList<>();
        //columns: ["day", "orders", "products", "amount"]
        columnList.add("day");
        columnList.add("orders");
        columnList.add("products");
        columnList.add("amount");
        HashMap<String, Object> goodsStatisticHashmap = new HashMap<>();
        goodsStatisticHashmap.put("columns", columnList);
        goodsStatisticHashmap.put("rows", goodsStatisticList);
        return goodsStatisticHashmap;
    }
}
