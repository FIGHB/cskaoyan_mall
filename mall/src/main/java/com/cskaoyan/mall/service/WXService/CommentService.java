package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.vo.BaseRespVo;

public interface CommentService {

    BaseRespVo queryAllComment(int valueId,int type, int showType, int page, int size);

    BaseRespVo queryAllComment(int valueId,int type);

    BaseRespVo insert(Comment comment,String username);

}
