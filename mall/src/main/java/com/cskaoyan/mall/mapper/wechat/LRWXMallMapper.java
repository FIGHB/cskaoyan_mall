package com.cskaoyan.mall.mapper.wechat;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Keyword;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
@Repository
public interface LRWXMallMapper {

    List<Category> queryCategoryList();

    Category queryFirstLevel1Category();

    List<Category> queryCurrentSubCategory(Integer pid);

    @Select("select count(*) from cskaoyan_mall_goods where deleted = false")
    Integer queryGoodsCount();

    Keyword queryDefaultKeyword();

    List<Keyword> queryHotKeywordList();

    @Select("select keyword from cskaoyan_mall_keyword where keyword like #{keyword} and deleted = false")
    List<String> querySearchHelper(@Param("keyword") String keyword);

    @Select("select keyword from cskaoyan_mall_search_history where user_id = #{userId} and deleted = false")
    List<Map> queryHistoryKeywordList(int userId);

    @Select("select id from cskaoyan_mall_user where username = #{username} and deleted = false")
    int queryUserIdByUserName(String username);

    @Update("update cskaoyan_mall_search_history set deleted = true where user_id = #{userId}")
    Integer deleteSearchHistory(int userId);

    @Select("select id from cskaoyan_mall_user where username = #{username} and deleted = false")
    int selectUserIdByUserName(String username);
}
