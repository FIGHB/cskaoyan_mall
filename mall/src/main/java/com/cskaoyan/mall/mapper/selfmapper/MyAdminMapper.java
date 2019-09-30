package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.vo.List_AdminVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
public interface MyAdminMapper {

    List<Admin> selectByExample(int offset, int limit, String sort, String order);

    List<Admin> selectByUsername(String username, int offset, int limit, String sort, String order);

    @Select("select count(*) from cskaoyan_mall_admin")
    int selectCountAdmin();

    List<Map<String, Object>> selectAllRole();

    List<Log> getAllLogList(int offset, int limit, String sort, String order);

    @Select("select count(*) from cskaoyan_mall_log")
    int selectCountLog();

    int insertStorge(Storage storage);

    int selectStorgeId(Storage storage);

    int addAdmin(List_AdminVo adminVo);

    int selectAdminId(List_AdminVo adminVo);

    int updateAdminByPrimaryKey(List_AdminVo adminVo);

    Admin selectAdminVoById(Integer id);
}
