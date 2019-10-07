package com.cskaoyan.mall.mapper.WXMapper;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.CommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxfCommentMapper {

    List<Comment> queryAllComment(@Param("type") int type,@Param("valueId") int valueId);

    Comment selectLastInsert();
}
