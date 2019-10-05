package com.cskaoyan.mall.service.steve;

import com.cskaoyan.mall.vo.steve.BrandListVo;
import com.cskaoyan.mall.vo.steve.WeChatCategoryVo;
import com.cskaoyan.mall.vo.steve.WeChatGoodsReceiveData;
import com.cskaoyan.mall.vo.steve.WechatGoodsList;

/**
 * @author Steve
 * @date 2019/10/5-15:28
 */
public interface WechatGoodsServices {

    WechatGoodsList queryWeChatGoodsList(WeChatGoodsReceiveData weChatGoodsReceiveData,String username);

    WeChatCategoryVo queryCategoryList(int id);

}
