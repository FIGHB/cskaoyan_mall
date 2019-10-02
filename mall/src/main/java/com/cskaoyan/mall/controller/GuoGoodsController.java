package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.service.GuoGoodsService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CommentShow;
import com.cskaoyan.mall.vo.ItemsList;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class GuoGoodsController {

    @Autowired
    GuoGoodsService guoGoodsService;

    @RequestMapping("/comment/list")
    @ResponseBody
    public BaseRespVo getCommentList(CommentShow commentShow){
        PageHelper.startPage(commentShow.getPage(),commentShow.getLimit(),commentShow.getSort()+" "+commentShow.getOrder());
        List<Comment> commentList=guoGoodsService.getCommentList(commentShow);
        ItemsList<Comment> commentItemsList = UserController.itemsList(commentList);
        BaseRespVo ok = BaseRespVo.ok(commentItemsList);
        return ok;
    }
    @RequestMapping("/comment/delete")
    @ResponseBody
    public BaseRespVo deleteComment(@RequestBody Comment comment){
        guoGoodsService.deleteCommentById(comment.getId());
        BaseRespVo ok = BaseRespVo.ok(null);
        return ok;
    }
}
