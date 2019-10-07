package com.cskaoyan.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommentUserInfo {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private String content;

    private String[] picList;

    private UserInfoWxf userInfo;

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getPicList() {
        return picList;
    }

    public void setPicList(String[] picList) {
        this.picList = picList;
    }

    public UserInfoWxf getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoWxf userInfo) {
        this.userInfo = userInfo;
    }
}
