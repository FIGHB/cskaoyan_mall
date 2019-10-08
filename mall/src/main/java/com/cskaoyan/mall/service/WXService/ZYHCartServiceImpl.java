package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.mapper.WXMapper.GrouponListMapper;
import com.cskaoyan.mall.mapper.WXMapper.ZYHCartMapper;
import com.cskaoyan.mall.vo.CartCheckedBean;
import com.cskaoyan.mall.vo.CartCheckedVO;
import com.cskaoyan.mall.vo.CartTotal;
import com.sun.xml.internal.fastinfoset.util.ValueArrayResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ZYHCartServiceImpl implements ZYHCartService {
    @Autowired
    ZYHCartMapper cartMapper;
    @Autowired
    GrouponListMapper grouponListMapper;

    @Override
    public CartCheckedVO checked(CartCheckedBean checkedBean, String username) {
        CartCheckedVO cartCheckedVO = new CartCheckedVO();
        CartTotal cartTotal = new CartTotal();
        int checked = checkedBean.getIsChecked();
        int userId = grouponListMapper.queryUserIdByName(username);
        Boolean isChecked = null;


        if (checked == 0){
            isChecked = false;
        }
        if (checked == 1){
            isChecked = true;
        }

        for (int productId : checkedBean.getProductIds()){
            cartMapper.updateCartChecked(productId,isChecked);
        }
        List<Cart> cartList = cartMapper.queryCartListByUserId(userId);
        cartCheckedVO.setCartList(cartList);

        float checkedGoodsAmount = 0;
        int checkedGoodsCount = 0;
        float goodsAmount = 0;
        int goodsCount = 0;
        for (Cart cart : cartList){
            goodsCount += cart.getNumber();
            goodsAmount += cart.getNumber() * cart.getPrice().floatValue();
            if (cart.getChecked() == true){
                checkedGoodsCount += cart.getNumber();
//                checkedGoodsAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
                checkedGoodsAmount += cart.getNumber() * cart.getPrice().floatValue();
            }
        }
        cartTotal.setGoodsCount(goodsCount);
        cartTotal.setGoodsAmount(goodsAmount);
        cartTotal.setCheckedGoodsAmount(checkedGoodsAmount);
        cartTotal.setCheckedGoodsCount(checkedGoodsCount);
        cartCheckedVO.setCartTotal(cartTotal);
        return cartCheckedVO;
    }
}
