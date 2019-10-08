package com.cskaoyan.mall.controller.ChenWuController;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.service.ChenWuService.CartService;
import com.cskaoyan.mall.service.ChenWuService.OrderService;
import com.cskaoyan.mall.service.WXService.WxfOrderService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChenWuWx.CWOrderDetail;
import com.cskaoyan.mall.vo.ChenWuWx.CWSubmit;
import com.cskaoyan.mall.vo.ChenWuWx.OrderInfo;
import com.cskaoyan.mall.vo.ConfirmBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/order/")
public class CHOrderController {
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    WxfOrderService wxfOrderService;
    @PostMapping("submit")
    public BaseRespVo<Integer> submit(@RequestBody CWSubmit cwSubmit) {
        orderService.insertOrder();
        ConfirmBean confirmBean = new ConfirmBean();
        confirmBean.setOrderId(7);
        return BaseRespVo.ok(confirmBean);
        /*Subject subject = SecurityUtils.getSubject();//shiro
        String username = (String) subject.getPrincipal();
        //根据username拿到id再去查表,先把货物goods插入order_goods之后再查
        int useId = cartService.queryIdByName(username);
        Integer cartId = cwSubmit.getCartId();
        //逻辑分发:是购物车来的订单还是立即购买来的
        if (cartId != 0) {
            orderService.submitOrder(useId, cwSubmit);
        } else {
            orderService.submitOrderUseCart(useId, cwSubmit);
        }

        int orderId = orderService.queryOrderIdByUserId(useId);
        BaseRespVo<Integer> integerBaseRespVo = new BaseRespVo<>();
        integerBaseRespVo.setErrno(0);
        integerBaseRespVo.setErrmsg("成功");
        integerBaseRespVo.setData(orderId);
        return integerBaseRespVo;*/
    }


    /**
     *暂时无法抓取报文所以不知道这里要实现的目的
     *
     *订单的预定支付页面
     */

    @PostMapping("prepay")
    public BaseRespVo prepay(@RequestBody Map<String ,Object> map) {
        //立即购买和购物车都会传来prepay，它的orderid有string和integer两种情况，判断属于哪种并做分发
        Object orderId1 = map.get("orderId");
        Integer orderId = 0;
        if (orderId1 instanceof String) {
            orderId= Integer.valueOf((String) orderId1);
        } else if (orderId1 instanceof Integer) {
            orderId=(Integer)orderId1;
        }
        if (orderService.updateOrderPrepay(orderId) == 0) {
            return BaseRespVo.fail(724, "货源不足无法支付");
        }
        return BaseRespVo.ok("成功");

    }



    /**
     * showtype作用？订单的五种状态：全部0，代付款1，代发货2，待收货3，待评价4
     */
    /*@GetMapping("list")
    public BaseRespVo<CWOrderListVo> list(int showType,int page, int size) {
        //psge是第几页,size是一页包含的订单条目数，要实现分页等
        BaseRespVo<CWOrderListVo> cwOrderListVoBaseRespVo = new BaseRespVo<>();
        CWOrderListVo cwOrderListVo = new CWOrderListVo();

        //要把所有list
        cwOrderListVo = orderService.queryCWOrderListVo(showType,page,size);

        cwOrderListVoBaseRespVo.setErrmsg("成功");
        cwOrderListVoBaseRespVo.setErrno(0);
        cwOrderListVoBaseRespVo.setData(cwOrderListVo);
        return cwOrderListVoBaseRespVo;
    }*/

    @GetMapping("detail")
    public BaseRespVo<CWOrderDetail> detail(Integer orderId) {
        CWOrderDetail cwOrderDetail = new CWOrderDetail();
        List<OrderGoods> orderGoodsList= orderService.queryOrderGoodsList(orderId);

        OrderInfo orderInfo = orderService.queryOrderInfo(orderId);

       cwOrderDetail.setOrderGoods(orderGoodsList);
       cwOrderDetail.setOrderInfo(orderInfo);
        BaseRespVo ok = BaseRespVo.ok(cwOrderDetail);
        return ok;
    }

    /**
     * 王小凤
     */
    @RequestMapping("/refund")
    public BaseRespVo refund(@RequestBody OrderGoods orderGoods){
        BaseRespVo delete = wxfOrderService.delete(orderGoods);
        return delete;
    }
}
