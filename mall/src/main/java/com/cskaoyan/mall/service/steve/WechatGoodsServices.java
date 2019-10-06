package com.cskaoyan.mall.service.steve;

import com.cskaoyan.mall.vo.steve.*;

/**
 * @author Steve
 * @date 2019/10/5-15:28
 */
public interface WechatGoodsServices {

    WechatGoodsList queryWeChatGoodsList(WeChatGoodsReceiveData weChatGoodsReceiveData,String username);

    WeChatCategoryVo queryCategoryList(int id);

    WeChatGoodsDetailVo queryGoodsDetail(int id);

    WeChataGoodsListVo queryWeChatGoodsForRelated(int id);
}
