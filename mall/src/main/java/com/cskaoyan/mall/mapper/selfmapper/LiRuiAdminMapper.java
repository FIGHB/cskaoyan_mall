package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.vo.List_AdminVo;
import com.cskaoyan.mall.vo.SysPermissionVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    @Select("select count(*) from cskaoyan_mall_log where deleted = false")
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

    @Select("select count(*) from cskaoyan_mall_role where deleted = false")
    int selectCountRole();

    List<Storage> getStorageList(int offset, int limit, String sort, String order, String key, String name);

    @Select("select count(*) from cskaoyan_mall_storage where deleted = false")
    int selectCountStorage();

    //查询是否有这个 permission 存在，不需要看 deleted
    @Select("select count(*) from cskaoyan_mall_permission where role_id = #{roleId} and permission = #{permission}")
    int selectPermissionByPermission(int roleId, String permission);

    @Insert("insert into cskaoyan_mall_permission (role_id,permission,add_time, update_time) values (#{roleId}, #{permission}, #{date}, #{date})")
    int addPermission(@Param("roleId") int roleId,@Param("permission") String permission,@Param("date") Date date);

    @Update("update cskaoyan_mall_permission set deleted = false, update_time = #{date} where role_id = #{roleId} and permission = #{permission}")
    void setPermissionNotDeleted(int roleId, String permission, Date date);

    List<String> selectPermissionByRoleId(String roleId);

    //没有deleted列
    @Select("select pid, id, label from cskaoyan_mall_permission_details where parent_id = #{id}")
    List<SysPermissionVo> selectSysPermissionByParentId(int id);
    //没有deleted列
    @Select("select id, label, api from cskaoyan_mall_permission_details where parent_id = #{id}")
    List<SysPermissionVo> selectSysPermissionByParentId2(int id);

    @Update("update cskaoyan_mall_permission set deleted = true where role_id = #{roleId}")
    void setAllPermissionDeletedByRoleId(int roleId);

}
