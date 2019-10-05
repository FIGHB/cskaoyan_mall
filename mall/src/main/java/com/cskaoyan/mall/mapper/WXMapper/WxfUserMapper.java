package com.cskaoyan.mall.mapper.WXMapper;

import org.apache.ibatis.annotations.Param;

public interface WxfUserMapper {
    int queryUserId(@Param("username") String username);
}
