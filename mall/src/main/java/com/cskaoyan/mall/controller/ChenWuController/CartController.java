package com.cskaoyan.mall.controller.ChenWuController;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.service.ChenWuService.CartService;
import com.cskaoyan.mall.service.ChenWuService.OrderService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChenWuWx.CWCheckVo;
import com.cskaoyan.mall.vo.ChenWuWx.CartTotal;
import com.cskaoyan.mall.vo.ChenWuWx.CartTotalVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**陈武
 * 这是购物车的转发，基本还没有测试
 */
@RestController
@RequestMapping("wx/cart/")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;//以便修改库存


    int getUserId() {
        Subject subject = SecurityUtils.getSubject();//shiro
        String username = (String) subject.getPrincipal();//根据username拿到id再去查表
        int id = cartService.queryIdByName(username);
        return id;

    }

    /**
     * 陈武
     * 未测试
     * 先等商品详情
     * 多用逆向工程
     */
    @PostMapping("index")
    public BaseRespVo<CartTotalVo> index() {
        BaseRespVo<CartTotalVo> cartTotalVoBaseRespVo = new BaseRespVo<>();
        CartTotalVo cartTotalVo = new CartTotalVo();
        CartTotal cartTotal = new CartTotal();
        cartTotal = cartService.queryCartTotal();//这是购物车的价格数量等信息，不直接存在于数据库需要自己查询处理
        List<Cart> cartList = cartService.queryCartList();//这是添加到购物车的商品信息
        cartTotalVo.setCartTotal(cartTotal);
        cartTotalVo.setCartList(cartList);
        /*当做有一个userid*/
        cartTotalVoBaseRespVo.setErrmsg("成功");
        cartTotalVoBaseRespVo.setErrno(0);
        cartTotalVoBaseRespVo.setData(cartTotalVo);
        return cartTotalVoBaseRespVo;
    }


    /**
     * 未测试
     */
    @PostMapping("add")
    public BaseRespVo<Integer> add(int goodsId, short number, int productId) {
        //这里要利用传来的参数查询出完整的商品信息cart再插入cart表中方便index使用
        int useId = getUserId();
        BaseRespVo<Integer> integerBaseRespVo = new BaseRespVo<>();
        integerBaseRespVo.setErrno(0);
        integerBaseRespVo.setErrmsg("成功");
        /*把传来的数据插入购物车数据库，然后统计number列总和并传回
         * 逆向工程不能多表查询所以颜培杰把goods和product查出来再塞回购物车那张表，我直接使用逆向可能做错了
         * 这里比较繁琐要查三张表
         * check里有id
         * */
        int a = cartService.selectAllNumber(useId, goodsId, number, productId);//在这里依次完成插入和统计数量的功能
        //这是总购物车商品数量,鑫哥说用cart表。
        Integer A = new Integer(a);
        integerBaseRespVo.setData(A);
        return integerBaseRespVo;
    }

    /**
     * 更新：这步是立即购买的操作，在cart下说明是通过购物车实现的：
     * 立即插入一条数据到cart表再在操作之后将它的对应列名delete的值设为1（逻辑删除）从而导致它在购物车模块下不可选。
     * 这的number可用于order。
     *在立即购买firstadd中传的请求参数可以用来获取商品goods的信息以便处理之后
     * 插入order表（如零售价格乘以number之后就是订单表中所需的商品总价格）以及在product中减少对应的库存。还要执行的
     * 一个操作是将当前的商品添加到cart表中之后delete设为-1.
     */
    @PostMapping("fastadd")
    public BaseRespVo<Integer> fastadd(Integer goodsId, short number, Integer productId) {
        BaseRespVo<Integer> integerBaseRespVo = new BaseRespVo<>();
        integerBaseRespVo.setErrno(0);

        int data = cartService.queryCartIdByProductId(goodsId, number,productId);//这是cartId

        /*此处执行其他操作*/
        //orderService.insertNumberToOrderByGoodsThenChangeProduct(goodsId, number, productId);写在一起不方便单个调用
        //要在cart中插入一行数据
        //Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        //BigDecimal retailPrice = goods.getRetailPrice();
        //BigDecimal bigDecimal = new BigDecimal((long) number);
        //BigDecimal goodsprice =  retailPrice.multiply(bigDecimal);//这是商品总费用
        //int i = orderService.insertGoodsPrice(goodsId, goodsprice, productId);
        int userId = getUserId();
        //在这里依次完成在cart表的插入和统计数量的功能，i是car表购物车的商品总数量这里用不到
        int i = cartService.selectAllNumber(userId, goodsId, number, productId);

        GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(productId);
        Integer number1 = goodsProduct.getNumber();
        int i1 = number1 - number;
        if (i1 > 0) {
            goodsProduct.setNumber(i1);
            goodsProductMapper.updateByPrimaryKey(goodsProduct);
        }
        //orderService.changeNumberInProduct(goodsId, number, productId);


        integerBaseRespVo.setErrmsg("成功");
        integerBaseRespVo.setData(data);
        return integerBaseRespVo;
    }

    @PostMapping("update")
    public BaseRespVo update(Integer goodsId, Integer id, short number, Integer productId) {
        //id是cart的id,这个方法主要执行对cart表的number的增加或减少操作(在库存允许范围内)
        BaseRespVo baseRespVo = new BaseRespVo();
        cartService.updateNumber(goodsId, id, number, productId);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    @PostMapping("delete")
    public BaseRespVo<CartTotalVo> delete(String[] productIds) {
        Subject subject = SecurityUtils.getSubject();//shiro
        String username = (String) subject.getPrincipal();//根据username拿到id再去查表
        int id = cartService.queryIdByName(username);
        BaseRespVo<CartTotalVo> cartTotalVoBaseRespVo = new BaseRespVo<>();
        CartTotalVo cartTotalVo = new CartTotalVo();
        CartTotal cartTotal = new CartTotal();
        List<Cart> cartList = cartService.deleteCartList(productIds);//这是添加到购物车的商品信息
        cartTotal = cartService.deleteCartTotal(productIds);//这是购物车的价格数量等信息，不直接存在于数据库需要自己查询处理
        cartTotalVo.setCartTotal(cartTotal);
        cartTotalVo.setCartList(cartList);
        cartTotalVoBaseRespVo.setErrmsg("成功");
        cartTotalVoBaseRespVo.setErrno(0);
        cartTotalVoBaseRespVo.setData(cartTotalVo);
        return cartTotalVoBaseRespVo;
    }

    /**
     * 老师的就只是返回这些，暂时不明白它要我执行什么操作
     * 更新：应该要在order中插入一部分数据，起码把order的order_status插入(默认101吧后面其他操作还会变)
     * 因为这是购物车和商品详情立即购买页面共用的接口
     * cartId好像有问题因为商品详情立即购买那里的请求参数也有一个cartId.
     */
    @PostMapping("checkout")
    public BaseRespVo checkout(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId) {
        BaseRespVo baseRespVo = new BaseRespVo();
        int userId = getUserId();
        orderService.InsertStatusValue(userId,cartId, addressId, couponId, grouponRulesId);
        baseRespVo.setErrmsg("系统内部错误");
        baseRespVo.setErrno(502);
        return baseRespVo;
    }

    @PostMapping("checked")
    public BaseRespVo<CartTotalVo> checked(@RequestBody CWCheckVo cwCheckVo) {
        BaseRespVo<CartTotalVo> cartTotalVoBaseRespVo = new BaseRespVo<>();
        CartTotalVo cartTotalVo = new CartTotalVo();
        CartTotal cartTotal = new CartTotal();
        cartTotal = cartService.queryCartTotal();
        List<Cart> cartList=cartService.queryCartList(cwCheckVo); //这是添加到购物车的商品信息
        cartTotalVo.setCartTotal(cartTotal);
        cartTotalVo.setCartList(cartList);
        cartTotalVoBaseRespVo.setErrmsg("成功");
        cartTotalVoBaseRespVo.setErrno(0);
        cartTotalVoBaseRespVo.setData(cartTotalVo);
        return cartTotalVoBaseRespVo;

    }

//    @GetMapping("goodscount")
//    public BaseRespVo goodscount() {
//        BaseRespVo baseRespVo = new BaseRespVo();//data是购物车中商品的总数
//        CartTotal cartTotal = cartService.queryCartTotal();
//        int goodsCount = cartTotal.getGoodsCount();
//        baseRespVo.setData(goodsCount);
//        baseRespVo.setErrmsg("成功");
//        baseRespVo.setErrno(0);
//        return baseRespVo;
//    }
}
