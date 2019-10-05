package com.cskaoyan.mall.bean.wxbean;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.Coupon;

import java.util.List;

public class HomePageVO {
   private List<Ad> banner;
   private List<Brand> brandList;
   private List<Category> channel; //一级分类目录
   private List<Coupon> couponList; //优惠券
   private List<FloorGoodList> floorGoodsList;
   private List<GroupOn> grouponList;
   private List<Goods> hotGoodsList;
   private List<Goods> newGoodsList;
   private List<Topic> topicList;

    public List<Ad> getBanner() {
        return banner;
    }

    public void setBanner(List<Ad> banner) {
        this.banner = banner;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Category> getChannel() {
        return channel;
    }

    public void setChannel(List<Category> channel) {
        this.channel = channel;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public List<FloorGoodList> getFloorGoodsList() {
        return floorGoodsList;
    }

    public void setFloorGoodsList(List<FloorGoodList> floorGoodsList) {
        this.floorGoodsList = floorGoodsList;
    }

    public List<GroupOn> getGrouponList() {
        return grouponList;
    }

    public void setGrouponList(List<GroupOn> grouponList) {
        this.grouponList = grouponList;
    }

    public List<Goods> getHotGoodsList() {
        return hotGoodsList;
    }

    public void setHotGoodsList(List<Goods> hotGoodsList) {
        this.hotGoodsList = hotGoodsList;
    }

    public List<Goods> getNewGoodsList() {
        return newGoodsList;
    }

    public void setNewGoodsList(List<Goods> newGoodsList) {
        this.newGoodsList = newGoodsList;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }
}
