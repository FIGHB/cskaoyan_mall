package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.vo.FeedbackGuo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/*国旭*/
public interface FeedbackMapperGuo {
    List<FeedbackGuo> getFeedbackList();
    List<FeedbackGuo> getFeedbackListBySreen(@Param("id") Integer id,@Param("username") String username);
}
