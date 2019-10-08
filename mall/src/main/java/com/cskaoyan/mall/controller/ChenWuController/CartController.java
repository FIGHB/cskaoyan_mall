package com.cskaoyan.mall.controller.ChenWuController;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.service.ChenWuService.CartService;
import com.cskaoyan.mall.service.ChenWuService.OrderService;
import com.cskaoyan.mall.service.wechat.LRWXMallService;
import com.cskaoyan.mall.service.wechat.LRWXMallServiceImpl;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChenWuWx.CWCheckVo;
import com.cskaoyan.mall.vo.ChenWuWx.CartTotal;
import com.cskaoyan.mall.vo.ChenWuWx.CartTotalVo;
import com.cskaoyan.mall.vo.GuoVo.GuoCart;
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
     * 少写代码
     */
    @PostMapping("add")
    @ResponseBody
    public BaseRespVo<Integer> add(@RequestBody GuoCart guoCart) {
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
        int a = cartService.selectAllNumber(useId, guoCart.getGoodsId(), guoCart.getNumber(), guoCart.getProductId());//在这里依次完成插入和统计数量的功能
        //这是总购物车商品数量,鑫哥说用cart表。
        Integer A = new Integer(a);
        integerBaseRespVo.setData(A);
        return integerBaseRespVo;
    }




    private int getUserIdBySecurityUtils() {
        LRWXMallService lrwxMallService = new LRWXMallServiceImpl();
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if(username == null) return -1;
        return lrwxMallService.getUserIdByUsername(username);
    }
}
