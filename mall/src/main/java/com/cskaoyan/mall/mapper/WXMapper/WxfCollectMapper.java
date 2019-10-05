package com.cskaoyan.mall.mapper.WXMapper;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.wxbean.CollectList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxfCollectMapper {

    List<CollectList> queryAllCollect(@Param("type") int type,@Param("userId") int userId);

    int isCollect(Collect collect);

    int delete(Collect collect);
}
