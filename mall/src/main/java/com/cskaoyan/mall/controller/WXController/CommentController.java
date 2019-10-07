package com.cskaoyan.mall.controller.WXController;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.service.WXService.CommentService;
import com.cskaoyan.mall.utils.GetUserName;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.prefs.BackingStoreException;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("/wx/comment/list")
    public BaseRespVo list(int valueId,int type,int showType,int page,int size){
        BaseRespVo baseRespVo = commentService.queryAllComment(valueId,type,showType,page,size);
        return baseRespVo;
    }
    @RequestMapping("/wx/comment/count")
    public BaseRespVo count(int type,int valueId){
        BaseRespVo baseRespVo = commentService.queryAllComment(valueId,type);
        return baseRespVo;
    }

    @RequestMapping("/wx/comment/post")
    public BaseRespVo post(@RequestBody Comment comment){
        String userName = GetUserName.getUserName();
        if(userName==null){
            return BaseRespVo.fail(501,"请登录");
        }
        BaseRespVo insert = commentService.insert(comment,userName);
        return insert;
    }

}
