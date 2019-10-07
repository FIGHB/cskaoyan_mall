package com.cskaoyan.mall.bean.wechat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserCouponBean {
    private int id;
    private String desc;
    private Double discount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date endTime;
    private double min;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date startTime;
    private String tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
