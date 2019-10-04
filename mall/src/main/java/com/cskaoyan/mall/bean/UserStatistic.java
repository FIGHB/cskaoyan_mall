package com.cskaoyan.mall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author 赵亚云
 */
public class UserStatistic {
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date day;
    int users;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserStatistic{" +
                "day=" + day +
                ", users=" + users +
                '}';
    }
}
