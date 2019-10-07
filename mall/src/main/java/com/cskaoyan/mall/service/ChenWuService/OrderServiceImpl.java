package com.cskaoyan.mall.service.ChenWuService;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.utils.CWDateBean;
import com.cskaoyan.mall.vo.ChenWuWx.CWOrderListVo;
import com.cskaoyan.mall.vo.ChenWuWx.CWOrderStatus;
import com.cskaoyan.mall.vo.ChenWuWx.CWSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    public static HashMap GetStatus() {
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
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    CartService cartService;


    @Override
    public int queryOrderIdByUserId(int useId) {
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andUserIdEqualTo(useId).andUserIdIsNotNull();
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        Order order = orderList.get(0);
        Integer id = order.getId();
        int i = id.intValue();
        return i;
    }

   /* @Override
    public CWOrderListVo queryCWOrderListVo(int page, int size) {
        CWOrderListVo cwOrderListVo = new CWOrderListVo();
        CWOrderStatus cwOrderStatus = new CWOrderStatus();
        OrderExample orderExample = new OrderExample();
        long count = orderMapper.countByExample(orderExample);//count
        int totalPages = (int) Math.ceil(count/size);



        //submit时就把各种价格插入了order表，拉出来就是了
        cwOrderStatus.setActualPrice();
        cwOrderStatus.setOrderGoodsList();
        cwOrderStatus.setGroupin();
        cwOrderStatus.setHandleOption();
        cwOrderStatus.setId();
        cwOrderStatus.setOrderStatusText();
        cwOrderStatus.setOrderSn();
        cwOrderStatus.isGroupin();
        cwOrderListVo.setTotalPages(totalPages);
        cwOrderListVo.setCwOrderStatus(cwOrderStatus);
        cwOrderListVo.setCount((int)count);
        return cwOrderListVo;
    }*/

    /**
     *提交订单时在order中添加数据
     * 先在order中增加吧，order_goods不知道要不要增加，cart不知道要不要变
     * 更新：cart表要先插后删一条数据，order_goods要增加，order要增加且利用cart表
     */
   /* @Override
    public void submitOrder(int useId, CWSubmit cwSubmit) {

        //OrderExample orderExample = new OrderExample();
       // OrderExample.Criteria criteria = orderExample.createCriteria();
        //criteria.andUserIdEqualTo(useId).andUserIdIsNotNull();
        //塞进一个bean里在传给sql层，首先获取各种成员变量的所需数据
        Date date = new Date();
        String orderSn = CWDateBean.getCurrentTime(date);
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andUserIdEqualTo(useId);

        Integer cartId = cwSubmit.getCartId();
        Cart cart = cartMapper.selectByPrimaryKey(cartId);
        BigDecimal price = cart.getPrice();
        Short number = cart.getNumber();
        BigDecimal goodsPrice = price.multiply(BigDecimal.valueOf(number));
        List<Address> addresses = addressMapper.selectByExample(addressExample);//到address中找name
        Address address = addresses.get(0);
        String consignee = address.getName();
        String mobile = address.getMobile();
        String address1 = address.getAddress();
        String message = cwSubmit.getMessage();
        BigDecimal freightPrice = (BigDecimal.valueOf(6));   //这个配送费用自己整一个，请求参数和数据库都没有
        BigDecimal couponPrice = (BigDecimal.valueOf(0));//本来是要查表及判断的
        BigDecimal integralPrice = (BigDecimal.valueOf(0));
        BigDecimal grouponPrice =


                Order order = new Order(useId,orderSn,goodsPrice,freightPrice,couponPrice,integralPrice,);
        int insert = orderMapper.insertSelective(order);

    }
*/
    @Override
    public void InsertStatusValue(int userId, Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId) {
        Order order = new Order();
        order.setOrderStatus((short) 101);
        order.setUserId(userId);
        orderMapper.insertSelective(order);
    }

    /**
     * 返回的是插入了商品总费用的order数据的id
     * 此处无用
     */
    @Override
    public int insertGoodsPrice(Integer goodsId, BigDecimal goodsprice, Integer productId) {
        Order order = new Order();
        order.setGoodsPrice(goodsprice);
        int i = cartMapper.insertGoodsPrice(order);
        return i;
    }

}
