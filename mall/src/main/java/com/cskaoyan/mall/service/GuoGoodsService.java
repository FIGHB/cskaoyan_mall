package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.vo.CommentShow;

import java.util.List;

public interface GuoGoodsService {

    List<Comment> getCommentList(CommentShow commentShow);

    void deleteCommentById(Integer id);
}
