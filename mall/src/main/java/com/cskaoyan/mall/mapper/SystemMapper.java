package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.bean.SystemExample;
import com.cskaoyan.mall.vo.ConfigExpressVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMapper {
    long countByExample(SystemExample example);

    int deleteByExample(SystemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(System record);

    int insertSelective(System record);

    List<System> selectByExample(SystemExample example);

    System selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") System record, @Param("example") SystemExample example);

    int updateByExample(@Param("record") System record, @Param("example") SystemExample example);

    int updateByPrimaryKeySelective(System record);

    int updateByPrimaryKey(System record);

    String queryMall(@Param("keyName") String keyName);

    void addMall(@Param("keyValue") String keyValue,@Param("keyName") String keyName);

    String queryExpress(@Param("keyName") String keyName);

    void addExpress(@Param("keyValue") String keyValue,@Param("keyName") String keyName);

    String queryOrder(@Param("keyName") String keyName);

    void addOrder(@Param("keyValue") String keyValue,@Param("keyName") String keyName);

    String queryWx(@Param("keyName") String keyName);

    void addWx(@Param("keyValue") String keyValue,@Param("keyName") String keyName);
}
