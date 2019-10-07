package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.vo.GrouponListVO;
import com.cskaoyan.mall.vo.MyGrouponVO;

public interface GrouponListService {
    GrouponListVO queryGrouponList(int page, int size);

    MyGrouponVO queryMyGroupOn(int showType, String username);
}
