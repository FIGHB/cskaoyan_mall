package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.Ad;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface WxfAdMapper {

    Ad selectByAddTime(@Param("add_time") Date add_time);

    Ad selectLastInsert();

    Ad[] queryByNameAndContent(Ad ad);

    long queryTotal(Ad ad);
}
