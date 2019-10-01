package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.vo.SearchHistoryGuo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/*国旭*/
public interface SearchHistoryMapperGuo {
    @Select("select id, user_id, keyword, add_time from cskaoyan_mall_search_history")
    List<SearchHistoryGuo> getSearchHistoryList();
    @Select("select user_id from cskaoyan_mall_search_history where id=#{id}")
    int getUserIdById(int id);
    @Select("select add_time from cskaoyan_mall_search_history where id=#{id}")
    String getAddTimeById(int id);
    List<SearchHistoryGuo> getSearchHistoryListByScreen(@Param("userId") Integer userId,@Param("keyword") String keyword);
}
