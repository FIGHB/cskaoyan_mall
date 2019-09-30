package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Log;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
public interface MyAdminMapper {



    List<Admin> selectByExample(int page, int limit, String sort, String order);

    @Select("select count(*) from cskaoyan_mall_admin")
    int selectCountAdmin();

    List<Map<String, Object>> selectAllRole();

    List<Log> getAllLogList(int offset, int limit, String sort, String order);

    @Select("select count(*) from cskaoyan_mall_log")
    int selectCountLog();
}
