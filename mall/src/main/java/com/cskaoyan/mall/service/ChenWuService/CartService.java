package com.cskaoyan.mall.service.ChenWuService;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.vo.ChenWuWx.CWCheckVo;
import com.cskaoyan.mall.vo.ChenWuWx.CartTotal;
import com.cskaoyan.mall.vo.ChenWuWx.CartTotalVo;

import java.util.List;

public interface CartService {

    int selectAllNumber(int userId,int goodsId, short number, int productId);

    CartTotal queryCartTotal();

    List<Cart> queryCartList();

    int queryIdByName(String username);

    int queryCartIdByProductId(Integer goodsId,short number,Integer productId);

    void updateNumber(Integer goodsId,Integer id,short number,Integer productId);

    CartTotal deleteCartTotal(String[] productIds);

    List<Cart> deleteCartList(String[] productIds);

    List<Cart> queryCartList(CWCheckVo cwCheckVo);
}
