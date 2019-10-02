package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.CommentExample;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.mapper.CommentMapperGuo;
import com.cskaoyan.mall.vo.CommentShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuoGoodsServiceImpl implements GuoGoodsService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentMapperGuo commentMapperGuo;

    @Override
    public List<Comment> getCommentList(CommentShow commentShow) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        if(commentShow.getUserId()!=null&&commentShow.getValueId()!=null){
            criteria.andUserIdEqualTo(commentShow.getUserId()).andValueIdEqualTo(commentShow.getValueId()).andDeletedEqualTo(false);
        }else if(commentShow.getUserId()==null&&commentShow.getValueId()!=null){
            criteria.andValueIdEqualTo(commentShow.getValueId()).andDeletedEqualTo(false);
        }else if(commentShow.getUserId()!=null&&commentShow.getValueId()==null){
            criteria.andUserIdEqualTo(commentShow.getUserId()).andDeletedEqualTo(false);
        }else {
            criteria.andIdIsNotNull().andDeletedEqualTo(false);
        }
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        return commentList;
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentMapperGuo.deleteCommentById(id);
    }
}
