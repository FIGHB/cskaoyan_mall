package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.vo.RoleIdVo;
import com.cskaoyan.mall.vo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface MyUserMapper {
    @Select("select password from cskaoyan_mall_admin where username = #{username} limit 0, 1")
    String queryPasswordByUsername(@Param("username")String username);

//    @Select("select role_ids from cskaoyan_mall_admin where username = #{username}")
    Integer[] queryRoleIdsByUsername(@Param("username")String username);

    RoleIdVo queryRoleIdVoByUsername(@Param("username")String username);

    @Select("select avatar, username name from cskaoyan_mall_admin where username = #{username} limit 0, 1")
    UserInfo queryAdminInfoByUserName(String username);
}
