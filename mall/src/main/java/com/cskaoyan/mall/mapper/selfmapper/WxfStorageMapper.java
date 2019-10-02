package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.StorageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxfStorageMapper {

    Storage selectByKey(@Param("key") String key);
}
