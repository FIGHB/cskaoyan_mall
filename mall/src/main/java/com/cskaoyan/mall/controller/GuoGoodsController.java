package com.cskaoyan.mall.controller;


import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.GuoGoodsService;
import com.cskaoyan.mall.vo.*;
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



    /*8888888888888888888888888888888888888888888888888888888888888888888888888888888888888*/
    /*@RequestMapping("/goods/list")
    @ResponseBody
    public BaseRespVo getGoodsList(GoodsShow goodsShow){
        PageHelper.startPage(goodsShow.getPage(),goodsShow.getLimit(),goodsShow.getSort()+" "+goodsShow.getOrder());
        List<Goods> goodsList=guoGoodsService.getGoodsList(goodsShow);
        ItemsList<Goods> goodsItemsList = UserController.itemsList(goodsList);
        BaseRespVo ok = BaseRespVo.ok(goodsItemsList);
        return ok;
    }*/
    @RequestMapping("/goods/detail")
    @ResponseBody
    public BaseRespVo getGoodsDetailByGoodsId(Integer id){
        GoodsDetail goodsDetail=guoGoodsService.getGoodsDetailByGoodsId(id);
        BaseRespVo ok = BaseRespVo.ok(goodsDetail);
        return ok;
    }
    @RequestMapping("/goods/catAndBrand")
    @ResponseBody
    public BaseRespVo getCategorys(){
        Categorys categorys=guoGoodsService.getCategorys();
        BaseRespVo ok = BaseRespVo.ok(categorys);
        return ok;
    }
    /*@RequestMapping("/goods/update")
    @ResponseBody
    public BaseRespVo updateDetails(@RequestBody GoodsDetail goodsDetail){
        guoGoodsService.updateDetails(goodsDetail);
        return null;
    }*/
}
