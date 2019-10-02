package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.vo.List_AdminVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
@Repository
public interface LiRuiAdminMapper {

    List<Admin> selectByExample(int offset, int limit, String sort, String order);

    List<Admin> selectByUsername(String username, int offset, int limit, String sort, String order);

    @Select("select count(*) from cskaoyan_mall_admin")
    int selectCountAdmin();

    List<Map<String, Object>> selectAllRole();

    List<Log> getAllLogList(int offset, int limit, String sort, String order, String name);

    @Select("select count(*) from cskaoyan_mall_log")
    int selectCountLog();

    int insertStorge(Storage storage);

    int selectStorgeId(Storage storage);

    int addAdmin(List_AdminVo adminVo);

    int selectAdminId(List_AdminVo adminVo);

    int addRole(Role role);

    int selectRoleId(Role role);

    int updateAdminByPrimaryKey(List_AdminVo adminVo);

    Admin selectAdminVoById(Integer id);

    int updateRoleByPrimaryKey(Role role);

    Role selectRoleById(Integer id);

    List<Role> selectRoleList(int offset, int limit, String sort, String order, String name);

    @Select("select count(*) from cskaoyan_mall_role")
    int selectCountRole();

    List<Storage> getStorageList(int offset, int limit, String sort, String order, String key, String name);

    @Select("select count(*) from cskaoyan_mall_storage")
    int selectCountStorage();

    List<String> selectPermissionByRoleId(String roleId);

    @Select("select count(*) from cskaoyan_mall_permission where role_id = #{roleId} and permission = #{permission}")
    int selectPermissionByPermission(int roleId, String permission);

    @Insert("insert into cskaoyan_mall_permission (role_id,permission) values (#{roleId}, #{permission})")
    int addPermission(int roleId, String permission);
}
