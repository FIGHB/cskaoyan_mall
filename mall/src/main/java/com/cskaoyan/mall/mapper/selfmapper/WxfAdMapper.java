package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.Ad;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface WxfAdMapper {

    Ad[] selectAllAd();

    Ad selectByAddTime(@Param("add_time") Date add_time);

    Ad selectLastInsert();

    Ad[] queryByNameAndContent(@Param("name") String name,@Param("content") String content);

    long queryTotal();
}
