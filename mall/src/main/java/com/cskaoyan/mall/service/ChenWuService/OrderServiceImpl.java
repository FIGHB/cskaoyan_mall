package com.cskaoyan.mall.service.ChenWuService;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.utils.CWDateBean;
import com.cskaoyan.mall.vo.ChenWuWx.*;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
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
    GoodsMapper goodsMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    SystemMapper systemMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;

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

    /**
     * 显示订单的页面
     *
     */
  /*  @Override
    public CWOrderListVo queryCWOrderListVo(int showType,int page, int size) {
        CWOrderListVo cwOrderListVo = new CWOrderListVo();
        CWOrderStatus cwOrderStatus = new CWOrderStatus();
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andIdIsNotNull();
        //List<Order> orders = orderMapper.selectByExample(orderExample);
        long count = orderMapper.countByExample(orderExample);//订单总数，sql那用count
        int totalPages = (int) Math.ceil(count/size);

        //submit时就把各种价格插入了order表，拉出来就是了
        //如何拉出来？用userid

        cwOrderStatus.setActualPrice();
        cwOrderStatus.setOrderGoodsList();
        cwOrderStatus.setHandleOption();
        cwOrderStatus.setId();
        cwOrderStatus.setGroupin();
        cwOrderStatus.setOrderSn();
        cwOrderStatus.setOrderStatusText();

        cwOrderListVo.setTotalPages(totalPages);
        cwOrderListVo.setCwOrderStatus(cwOrderStatus);
        cwOrderListVo.setCount((int)count);
        return cwOrderListVo;
    }*/

    /**这是商品详情过来的submit
     *提交订单时在order中添加数据
     * 先在order中增加吧，order_goods不知道要不要增加，cart不知道要不要变
     * 更新：cart表要先插后删一条数据，order_goods要增加，order要增加且利用cart表
     */
    @Override
    public void submitOrder(int useId, CWSubmit cwSubmit) {

        //OrderExample orderExample = new OrderExample();
       // OrderExample.Criteria criteria = orderExample.createCriteria();
        //criteria.andUserIdEqualTo(useId).andUserIdIsNotNull();
        //塞进一个bean里在传给sql层，首先获取各种成员变量的所需数据

        Order order = new Order();
        Date date = new Date();
        String orderSn = CWDateBean.getCurrentTime(date);
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andUserIdEqualTo(useId);

        Integer cartId = cwSubmit.getCartId();
        //因为在此之前立即购买的fastadd已经帮忙在cart表插入数据了，所以可以直接搜
        Cart cart = cartMapper.selectByPrimaryKey(cartId);
        BigDecimal price = cart.getPrice();//这是商品货品价格即零售价格
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
        //先判断人数是否满足团购优惠人数，若满足则查表否则为0.
        //更新：不用判断人数是否满足，因为我是创建订单的人，一开始人数肯定是不满足的，
        // 先把优惠金额写上在付款时再判断人数是否满足
        GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(cwSubmit.getGrouponRulesId());
        BigDecimal grouponPrice =grouponRules.getDiscount();//团购优惠价减免，先搁置
        BigDecimal orderPrice =goodsPrice.add(freightPrice).subtract(couponPrice);
        BigDecimal actualPrice =orderPrice.subtract(integralPrice);
        Short comments =number ;
        Date addTime= date;
        Date updateTime=date;
        Boolean deleted=false;

        order.setUserId(useId);
        order.setOrderSn(orderSn);
        order.setOrderStatus((short)101);
        order.setConsignee(consignee);//收货人名称
        order.setMobile(mobile);
        order.setAddress(address1);
        order.setMessage(message);
        order.setGoodsPrice(goodsPrice);
        order.setFreightPrice(freightPrice);
        order.setCouponPrice(couponPrice);
        order.setIntegralPrice(integralPrice);
        order.setGrouponPrice(grouponPrice);
        order.setOrderPrice(orderPrice);
        order.setActualPrice(actualPrice);
        order.setComments(number);
        order.setAddTime(addTime);
        order.setUpdateTime(updateTime);
        order.setDeleted(deleted);
        int orderid = orderMapper.insertOrderData(order);
        /*order插入数据之后要往groupon表插数据了*/
        Groupon groupon = new Groupon();
        groupon.setOrderId(orderid);
        groupon.setRulesId(cwSubmit.getGrouponRulesId());
        groupon.setUserId(useId);
        groupon.setCreatorUserId(useId);
        groupon.setUpdateTime(date);
        groupon.setShareUrl(cart.getPicUrl());
        groupon.setAddTime(date);
        groupon.setDeleted(false);
        groupon.setPayed(false);
        int grouponid = grouponMapper.insertGrouponData(groupon);
        Groupon groupon1 = grouponMapper.selectByPrimaryKey(grouponid);
        groupon1.setGrouponId(grouponid);
        grouponMapper.insertSelective(groupon1);
        /*order插入数据之后要往order_goods表插数据了*/
        OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOrderId(orderid);
        orderGoods.setGoodsId(cart.getGoodsId());
        orderGoods.setGoodsName(cart.getGoodsName());
        Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
        orderGoods.setGoodsSn(goods.getGoodsSn());
        orderGoods.setProductId(cart.getProductId());
        orderGoods.setNumber(cart.getNumber());
        orderGoods.setPrice(cart.getPrice());
        String[] specifications = cart.getSpecifications();
        orderGoods.setSpecifications(specifications);
        orderGoods.setPicUrl(goods.getPicUrl());
        orderGoods.setAddTime(date);
        orderGoods.setUpdateTime(date);
        orderGoods.setDeleted(false);
        orderGoodsMapper.insertSelective(orderGoods);
    }

    /**
     * 这是购物车过来的submit
     *
     */
    @Override
    public void submitOrderUseCart(int useId, CWSubmit cwSubmit) {
        //得到所有checked为true的cart数据,在order_goods中插入几行数据
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria1 = cartExample.createCriteria();
        criteria1.andCheckedEqualTo(true);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        //foreach做的是把多个checked为true的cart拿出来处理
        for (Cart cart1 : carts) {

            Order order = new Order();
            Date date = new Date();
            String orderSn = CWDateBean.getCurrentTime(date);
            AddressExample addressExample = new AddressExample();
            AddressExample.Criteria criteria = addressExample.createCriteria();
            criteria.andUserIdEqualTo(cart1.getUserId());

            BigDecimal price = cart1.getPrice();//这是商品货品价格即零售价格
            Short number = cart1.getNumber();
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
            //先判断人数是否满足团购优惠人数，若满足则查表否则为0.
            //更新：不用判断人数是否满足，因为我是创建订单的人，一开始人数肯定是不满足的，
            // 先把优惠金额写上在付款时再判断人数是否满足
            GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(cwSubmit.getGrouponRulesId());
            BigDecimal grouponPrice =grouponRules.getDiscount();//团购优惠价减免，先搁置
            BigDecimal orderPrice =goodsPrice.add(freightPrice).subtract(couponPrice);
            BigDecimal actualPrice =orderPrice.subtract(integralPrice);
            Short comments =number ;
            Date addTime= date;
            Date updateTime=date;
            Boolean deleted=false;



            order.setUserId(cart1.getUserId());
            order.setOrderSn(orderSn);
            order.setOrderStatus((short)101);
            order.setConsignee(consignee);//收货人名称
            order.setMobile(mobile);
            order.setAddress(address1);
            order.setMessage(message);
            order.setGoodsPrice(goodsPrice);
            order.setFreightPrice(freightPrice);
            order.setCouponPrice(couponPrice);
            order.setIntegralPrice(integralPrice);
            order.setGrouponPrice(grouponPrice);
            order.setOrderPrice(orderPrice);
            order.setActualPrice(actualPrice);
            order.setComments(number);
            order.setAddTime(addTime);
            order.setUpdateTime(updateTime);
            order.setDeleted(deleted);
            int orderid = orderMapper.insertOrderData(order);
            /*order插入数据之后要往groupon表插数据了*/
            Groupon groupon = new Groupon();
            groupon.setOrderId(orderid);
            groupon.setRulesId(cwSubmit.getGrouponRulesId());
            groupon.setUserId(cart1.getUserId());
            groupon.setCreatorUserId(cart1.getUserId());
            groupon.setUpdateTime(date);
            groupon.setShareUrl(cart1.getPicUrl());
            groupon.setAddTime(date);
            groupon.setDeleted(false);
            groupon.setPayed(false);
            int grouponid = grouponMapper.insertGrouponData(groupon);
            Groupon groupon1 = grouponMapper.selectByPrimaryKey(grouponid);
            groupon1.setGrouponId(grouponid);
            grouponMapper.insertSelective(groupon1);
            /*order插入数据之后要往order_goods表插数据了*/
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(orderid);
            orderGoods.setGoodsId(cart1.getGoodsId());
            orderGoods.setGoodsName(cart1.getGoodsName());
            Goods goods = goodsMapper.selectByPrimaryKey(cart1.getGoodsId());
            orderGoods.setGoodsSn(goods.getGoodsSn());
            orderGoods.setProductId(cart1.getProductId());
            orderGoods.setNumber(cart1.getNumber());
            orderGoods.setPrice(cart1.getPrice());
            String[] specifications = cart1.getSpecifications();
            orderGoods.setSpecifications(specifications);
            orderGoods.setPicUrl(goods.getPicUrl());
            orderGoods.setAddTime(date);
            orderGoods.setUpdateTime(date);
            orderGoods.setDeleted(false);
            orderGoodsMapper.insertSelective(orderGoods);

        }



    }

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

    @Override
    public List<OrderGoods> queryOrderGoodsList(Integer orderId) {
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        OrderGoodsExample.Criteria criteria = orderGoodsExample.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsExample);
        return orderGoodsList;
    }

    @Override
    public OrderInfo queryOrderInfo(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setActualPrice(order.getActualPrice());
        orderInfo.setAddTime(order.getAddTime());

        orderInfo.setAddress(order.getAddress());
        orderInfo.setConsignee(order.getConsignee());
        orderInfo.setCouponPrice(order.getCouponPrice());
        orderInfo.setFreightPrice(order.getFreightPrice());
        orderInfo.setGoodsPrice(order.getGoodsPrice());

        HandleOption handleOption =HandleOption.get(order.getOrderStatus(),order.getComments()!=0);
        orderInfo.setHandleOption(handleOption);

        orderInfo.setId(orderId);
        orderInfo.setMobile(order.getMobile());
        orderInfo.setOrderSn(order.getOrderSn());

        orderInfo.setOrderStatusText(handleOption.getStatusText());
        return orderInfo;
    }

    @Override
    public int updateOrderPrepay(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        Date date = new Date();
        String currentTime = CWDateBean.getCurrentTime(date);
        order.setOrderStatus((short)201);
        order.setPayId(""+currentTime+ RandomStringUtils.random(5));
        order.setPayTime(date);
        order.setUpdateTime(new Date());
        int i = orderMapper.insertSelective(order);
        return i;
    }


}
