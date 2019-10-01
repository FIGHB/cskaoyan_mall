package com.cskaoyan.mall.vo;
/*国旭*/
public class SearchHistoryGuo {
    private int id;
    private int userId;
    private String keyword;
    private String  addTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "SearchHistoryGuo{" +
                "id=" + id +
                ", userId=" + userId +
                ", keyword='" + keyword + '\'' +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
