package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.OrderStatistic;
import com.cskaoyan.mall.bean.UserStatistic;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author 赵亚云
 */
@Repository
public interface StatisticMapper  {
    //模块1 DATE_FORMAT(add_time,'%Y-%m-%d')
    @Select("select DATE_FORMAT(add_time, \"%Y-%m-%d\") as day, count(id) as users from cskaoyan_mall_user group by DATE_FORMAT(add_time, \"%Y-%m-%d\")")
    List<UserStatistic> userAddStatistic();

    //模块2
    //order表格 总价查询
    @Select("select sum(goods_price) from cskaoyan_mall_order group by STR_TO_DATE(add_time,'%Y-%m-%d'); ")
    List<Double> selectOrderAmount();
    //Order 订单数量查询
    @Select("select count(id) from  cskaoyan_mall_order group by STR_TO_DATE(add_time,'%Y-%m-%d');")
    List<Integer> selectOrderItems();
    //order表格 用户数量
    @Select("select count(distinct user_id ) from cskaoyan_mall_order  group by STR_TO_DATE(add_time,'%Y-%m-%d')")
    List<Integer> selectUserItems();
    //order表格 创建时间查询
    @Select("select distinct STR_TO_DATE(add_time,'%Y-%m-%d') as day from cskaoyan_mall_order")
    List<Date> selectOrderDay();

    //模块3
    //ordergoods表格 总价查询
    @Select("select sum(`number` * price) as amount from cskaoyan_mall_order_goods group by STR_TO_DATE(add_time,'%Y-%m-%d')")
    List<Double> selectGoodsAmount();
    //订单数量
    @Select("select count(order_id) as orders from cskaoyan_mall_order_goods group by STR_TO_DATE(add_time,'%Y-%m-%d')")
    List<Integer> selectGoodsOrderItems();
    //模块3中 创建时间
    @Select("select distinct add_time as day from cskaoyan_mall_order_goods")
    List<Date> selectGoodsDay();
    // 产品数量
    @Select("select sum(`number`) from cskaoyan_mall_order_goods group by STR_TO_DATE(add_time,'%Y-%m-%d')")
    List<Integer> selectGoodsNum();

    /*
    select og.`number` * og.price as amount ,
     og.`number` as orders,
     count(distinct o.user_id) as customers,
     og.add_time as day,
     amount/customers as pcr
     from cskaoyan_mall_order_goods og
     inner join cskaoyan_mall_order o
     on og.order_id = o.id group by og.add_time
     */
}
