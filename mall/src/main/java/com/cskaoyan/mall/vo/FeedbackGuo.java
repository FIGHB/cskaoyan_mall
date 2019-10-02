package com.cskaoyan.mall.vo;

import java.util.Arrays;

/*国旭*/
public class FeedbackGuo {
    private Integer id;
    private Integer userId;
    private String username;
    private String mobile;
    private String feedType;
    private String content;
    private Integer status;
    private Boolean hasPicture;
    private String[] picUrls;
    private String addTime;
    private String updateTime;
    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getHasPicture() {
        return hasPicture;
    }

    public void setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
    }


    public String[] getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String[] picUrls) {
        this.picUrls = picUrls;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "FeedbackGuo{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", feedType='" + feedType + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", hasPicture=" + hasPicture +
                ", picUrls=" + Arrays.toString(picUrls) +
                ", addTime='" + addTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
