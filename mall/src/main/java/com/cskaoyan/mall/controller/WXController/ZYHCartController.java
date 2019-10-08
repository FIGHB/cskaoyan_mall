package com.cskaoyan.mall.controller.WXController;

import com.cskaoyan.mall.service.WXService.ZYHCartService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CartCheckedBean;
import com.cskaoyan.mall.vo.CartUpdateVO;
import com.cskaoyan.mall.vo.ConfirmBean;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class ZYHCartController {
    @Autowired
    ZYHCartService cartService;

    @RequestMapping("/cart/checked")
    public BaseRespVo checked(@RequestBody CartCheckedBean checkedBean){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        return BaseRespVo.ok(cartService.checked(checkedBean, username));
    }

    @RequestMapping("/cart/update")
    public BaseRespVo updateCart(@RequestBody CartUpdateVO cartUpdateVO){
        cartService.updateCart(cartUpdateVO.getId(),cartUpdateVO.getNumber());
        return BaseRespVo.ok(null);
    }

    @RequestMapping("/cart/delete")
    public BaseRespVo deleteCart(@RequestBody CartCheckedBean checkedBean){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        return BaseRespVo.ok(cartService.deleteCart(checkedBean, username));
    }

    @RequestMapping("/order/confirm")
    public BaseRespVo confirm(@RequestBody ConfirmBean confirmBean){
        cartService.confirm(confirmBean.getOrderId());
        return BaseRespVo.ok(null);
    }
}
