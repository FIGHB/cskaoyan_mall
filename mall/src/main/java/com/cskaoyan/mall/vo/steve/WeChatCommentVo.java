package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.Comment;

import java.util.List;

/**
 * @author Steve
 * @date 2019/10/5-23:42
 */
public class WeChatCommentVo {
    long count;
    List<Comment> data;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }

}
