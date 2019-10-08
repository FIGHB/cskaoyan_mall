package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.vo.CartCheckedBean;
import com.cskaoyan.mall.vo.CartCheckedVO;

public interface ZYHCartService {
    CartCheckedVO checked(CartCheckedBean checkedBean, String username);

    void updateCart(int id, int number);

    CartCheckedVO deleteCart(CartCheckedBean checkedBean, String username);
}
