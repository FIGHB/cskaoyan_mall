package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.vo.LoginVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李锐
 */
@Repository
public interface LiAuthMapper {
    @Select("select count(*) from cskaoyan_mall_admin where username = #{username} and password = #{password}")
    int login(LoginVo loginVo);

    @Select("select name from cskaoyan_mall_role where id = #{id} and enabled = true and deleted = false")
    String getNameById(Integer id);

    @Select("select permission from cskaoyan_mall_permission where role_id = #{roleId} and deleted = false")
    List<String> getPermissionsByRoleIds(Integer roleId);

    @Select("select api from cskaoyan_mall_permission_details where api like #{perm2} limit 0, 1")
    String getPermissionApiByPerm(String perm2);
}
