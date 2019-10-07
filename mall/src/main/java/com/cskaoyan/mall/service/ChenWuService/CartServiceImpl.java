package com.cskaoyan.mall.service.ChenWuService;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.vo.ChenWuWx.CWCheckVo;
import com.cskaoyan.mall.vo.ChenWuWx.CartTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    CartMapper cartMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    /**
     * 获取购物车的数据
     */

    /**
     * 未测试，商品页面接口未完成无法获取库存信息
     */
    @Override
    public int selectAllNumber(int userId,int goodsId, short number, int productId) {
        //先完成插入操作，这里应该利用请求参数查询完整的商品信息再插入而非仅前端传回的
        Cart cart = new Cart();//有争议,insertbyselected.用逆向工程塞一部分。可以构造方法换个形式
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andIdEqualTo(goodsId);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        Goods goods = goodsList.get(0);
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria criteria1 = goodsProductExample.createCriteria();
        criteria1.andIdEqualTo(productId);
        List<GoodsProduct> goodsProducts = goodsProductMapper.selectByExample(goodsProductExample);
        GoodsProduct goodsProduct = goodsProducts.get(0);
        cart.setChecked(true);
        cart.setUserId(userId);
        cart.setGoodsId(goods.getId());
        cart.setGoodsSn(goods.getGoodsSn());
        cart.setGoodsName(goods.getName());
        cart.setProductId(goodsProduct.getId());
        cart.setPrice(goodsProduct.getPrice());
        //int number1 = goodsProduct.getNumber();不能插入总的库存量
        cart.setNumber((short)number);
        cart.setSpecifications(goodsProduct.getSpecifications());
        cart.setPicUrl(goods.getPicUrl());
        cart.setAddTime(goods.getAddTime());
        cart.setUpdateTime(goods.getUpdateTime());
        cart.setDeleted(goods.getDeleted());
        int insert = cartMapper.insert(cart);
        int l = (int) cartMapper.sumByColumnName("number");
        return l;
    }

    /**可以提取出去单独成一个工具类然后传个布尔值进去以及id，只是暂时没时间
     */
    @Override
    public CartTotal queryCartTotal() {
        int number = cartMapper.sumByColumnName("number");
        CartTotal cartTotal = new CartTotal();
        cartTotal.setGoodsCount(number);//checkedgoodscount与goodscount不同
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andCheckedEqualTo(true);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        //对查询结果进行处理：所有为true的商品的数量和总价
        int checkedGoodsCount = 0;//选中的商品总数
        float checkedGoodsAmount = 0;//选中的商品总价
        BigDecimal singleSum = null;
        BigDecimal totalSum = null;
        for (Cart cart : carts) {
            Short number1 = cart.getNumber();
            BigDecimal price = cart.getPrice();
            singleSum = price.multiply(BigDecimal.valueOf(number1));
            totalSum.add(singleSum);
        }
        cartTotal.setCheckedGoodsCount(checkedGoodsCount);
        cartTotal.setGoodsAmount(number);
        cartTotal.setCheckedGoodsAmount(totalSum.floatValue());
        return cartTotal;
    }

    @Override
    public List<Cart> queryCartList() {
        //查询cart的每条商品
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        return carts;
    }

    @Override
    public int queryIdByName(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andUsernameIsNotNull();
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        Integer id = user.getId();
        int i = id.intValue();
        return i;
    }

    /**在这执行了对product的库存判断和减少操作
     */
    @Override
    public int queryCartIdByProductId(Integer goodsId,short number,Integer productId) {
        GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(productId);
        Integer number1 = goodsProduct.getNumber();
        int i1 = number1 - number;
        goodsProduct.setNumber(i1);
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria criteria = goodsProductExample.createCriteria();
        criteria.andNumberGreaterThanOrEqualTo((int) number);
       // criteria.andIdEqualTo(productId);
        int i = goodsProductMapper.updateByExample(goodsProduct, goodsProductExample);
        return i;
    }

    /**
     * id是cart的id,这个方法主要执行对cart表的number的增加或减少操作(在库存允许范围内)
     */
    @Override
    public void updateNumber(Integer goodsId,Integer id,short number,Integer productId) {
        //传来的number是对应商品在购物车中修改后的数值，修改之前先判断是否在库存允许范围内
        Cart cart = cartMapper.selectByPrimaryKey(id);
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andNumberGreaterThanOrEqualTo(number);
        cart.setNumber(number);
        cartMapper.updateByExample(cart, cartExample);
        return;
    }

    @Override
    public CartTotal deleteCartTotal(String[] productIds) {
        int number = cartMapper.sumByColumnName("number");
        CartTotal cartTotal = new CartTotal();
        cartTotal.setGoodsCount(number);//checkedgoodscount与goodscount不同
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andCheckedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        //对查询结果进行处理：所有为true的商品的数量和总价
        int checkedGoodsCount = 0;//选中的商品总数
        float checkedGoodsAmount = 0;//选中的商品总价
        BigDecimal singleSum = null;
        BigDecimal totalSum = null;
        for (Cart cart : carts) {
            Short number1 = cart.getNumber();
            BigDecimal price = cart.getPrice();
            singleSum = price.multiply(BigDecimal.valueOf(number1));
            totalSum.add(singleSum);
        }
        cartTotal.setCheckedGoodsCount(checkedGoodsCount);
        cartTotal.setGoodsAmount(number);
        cartTotal.setCheckedGoodsAmount(totalSum.floatValue());
        return cartTotal;
    }

    @Override
    public List<Cart> deleteCartList(String[] productIds) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andIdIsNotNull();
        for (String productId : productIds) {
            int i = Integer.parseInt(productId);
            cartMapper.deleteByPrimaryKey(i);
        }
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        return carts;
    }


    @Override
    public List<Cart> queryCartList(CWCheckVo cwCheckVo) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();

        Integer[] productIds = cwCheckVo.getProductIds();
        List<Cart> carts = null;
        for (Integer productId : productIds) {
            criteria.andIdEqualTo(productId);
            List<Cart> carts1 = cartMapper.selectByExample(cartExample);//默认返回list
            for (Cart cart : carts1) {
                carts.add(cart);
            }
        }
        return carts;
    }
}
