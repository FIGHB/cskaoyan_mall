package com.cskaoyan.mall.controller.ChenWuController;
import com.cskaoyan.mall.service.ChenWuService.CartService;
import com.cskaoyan.mall.service.ChenWuService.OrderService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChenWuWx.CWOrderListVo;
import com.cskaoyan.mall.vo.ChenWuWx.CWOrderStatus;
import com.cskaoyan.mall.vo.ChenWuWx.CWSubmit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wx/order/")
public class CHOrderController {
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @PostMapping("submit")
    public BaseRespVo<Integer> submit(@RequestBody CWSubmit cwSubmit) {
        Subject subject = SecurityUtils.getSubject();//shiro
        String username = (String) subject.getPrincipal();
        //根据username拿到id再去查表,先把货物goods插入order_goods之后再查
        int useId = cartService.queryIdByName(username);
       /* orderService.submitOrder(useId,cwSubmit);*/
        int orderId = orderService.queryOrderIdByUserId(useId);
        BaseRespVo<Integer> integerBaseRespVo = new BaseRespVo<>();
        integerBaseRespVo.setErrno(0);
        integerBaseRespVo.setErrmsg("成功");
        integerBaseRespVo.setData(orderId);
        return integerBaseRespVo;
    }


    @PostMapping("prepay")
    public BaseRespVo<Integer> prepay(Integer orderId) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("订单不能支付");
        baseRespVo.setErrno(724);
        return baseRespVo;
    }

//    @GetMapping("list")
    public BaseRespVo<CWOrderListVo> list(int page, int size) {
        //psge是第几页,size是一页包含的订单条目数，要实现分页等
        BaseRespVo<CWOrderListVo> cwOrderListVoBaseRespVo = new BaseRespVo<>();
        CWOrderListVo cwOrderListVo = new CWOrderListVo();

        //CWOrderStatus cwOrderStatus = new CWOrderStatus();
        //cwOrderStatus = orderService.queryCWOrderStatus();

      /*  cwOrderListVo = orderService.queryCWOrderListVo(page,size);*/

        //cwOrderListVo.setCount();
        //cwOrderListVo.setCwOrderStatus(cwOrderStatus);
        //cwOrderListVo.setTotalPages();

        cwOrderListVoBaseRespVo.setErrmsg("成功");
        cwOrderListVoBaseRespVo.setErrno(0);
        cwOrderListVoBaseRespVo.setData(cwOrderListVo);
        return cwOrderListVoBaseRespVo;
    }
}
