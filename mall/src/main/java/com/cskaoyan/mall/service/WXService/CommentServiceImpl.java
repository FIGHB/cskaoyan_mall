package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.mapper.WXMapper.WxfCommentMapper;
import com.cskaoyan.mall.mapper.WXMapper.WxfUserMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.DetailRespVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    WxfCommentMapper wxfCommentMapper;
    @Autowired
    WxfUserMapper wxfUserMapper;

    @Override
    public BaseRespVo queryAllComment(int valueId,int type, int showType, int page, int size) {
        PageHelper.startPage(page,size);
        List<Comment> comments = wxfCommentMapper.queryAllComment(type,valueId);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);
        long total = commentPageInfo.getTotal();
        return BaseRespVo.ok(new DetailRespVo(comments,total,page));
    }

    @Override
    public BaseRespVo insert(Comment comment,String username) {
        Date date = new Date();
        comment.setAddTime(date);
        comment.setUpdateTime(date);
        int userId = wxfUserMapper.queryUserId(username);
        comment.setUserId(userId);
        commentMapper.insertSelective(comment);
        Comment commentResp = wxfCommentMapper.selectLastInsert();
        return BaseRespVo.ok(commentResp);
    }

    @Override
    public BaseRespVo queryAllComment(int valueId, int type) {
        List<Comment> comments = wxfCommentMapper.queryAllComment(type,valueId);
        return BaseRespVo.ok(comments);
    }

}
