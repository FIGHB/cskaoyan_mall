package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.vo.BaseRespVo;

import java.util.List;

public interface CollectService {
    BaseRespVo queryAllCollect(int type, int page, int size,String username);

    BaseRespVo addordelete(Collect collect,String username);
}
