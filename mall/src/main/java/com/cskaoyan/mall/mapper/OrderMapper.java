package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderExample;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.vo.MallBean.OrderGoodsBean;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("INSERT INTO `cskaoyan_mall_order` VALUES ('7', '1', '1006002', '101', '1', '18675730267', '软件新城2期c13', 'mymessage', '80.00', '16.00', '15.00', '14.00', '14.00', '13.50', '16.00', '1', '2019-04-22 11:45:32', 'abcd', '1', '2019-10-8 11:45:44', '2019-10-8 11:45:47', '20', '2019-10-8 21:45:57', '2019-10-8 21:46:00', '2019-10-8 21:46:03', '0')")
    void insertOrder();

    @Insert("INSERT INTO `cskaoyan_mall_order_goods` VALUES ('7', '7', '1006002', '轻奢纯棉刺绣水洗四件套', '1006002', '1', '1', '699.00', '[\\\"标准\\\"]', 'http://yanxuan.nosdn.127.net/8ab2d3287af0cefa2cc539e40600621d.png', '10', '2019-10-8 21:56:10', '2019-10-8 21:56:15', '0')")
    void insertOrderGoods();
}
