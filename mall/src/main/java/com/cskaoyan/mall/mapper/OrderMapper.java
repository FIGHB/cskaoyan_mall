package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderExample;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.vo.MallBean.OrderGoodsBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> queryOrderList(@Param("userId") Integer userId,@Param("orderSn") String orderSn,@Param("orderStatusArray") Short[] orderStatusArray);

    long queryOrderListTotal(@Param("userId") Integer userId,@Param("orderSn") String orderSn,@Param("orderStatusArray") Short[] orderStatusArray);

    Order queryOrderById(@Param("id") int id);

    List<OrderGoodsBean> queryOrderGoodsListByOrderId(@Param("orderId") int orderId);

    User queryUserById(@Param("userId") Integer userId);

    int insertOrderData(@Param("order") Order order);

}
