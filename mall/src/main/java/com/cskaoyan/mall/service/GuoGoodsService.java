package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.vo.Categorys;
import com.cskaoyan.mall.vo.CommentShow;
import com.cskaoyan.mall.vo.GoodsDetail;
import com.cskaoyan.mall.vo.GoodsShow;

import java.util.List;

public interface GuoGoodsService {

    List<Comment> getCommentList(CommentShow commentShow);

    void deleteCommentById(Integer id);

    List<Goods> getGoodsList(GoodsShow goodsShow);

    GoodsDetail getGoodsDetailByGoodsId(Integer id);

    Categorys getCategorys();

    Boolean updateDetails(GoodsDetail goodsDetail);
}
