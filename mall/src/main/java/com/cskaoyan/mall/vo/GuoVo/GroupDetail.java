package com.cskaoyan.mall.vo.GuoVo;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.User;

import java.util.List;

public class GroupDetail {
    User creator;
    Groupon groupon;
    List<User> joiners;
    OrderInfo orderInfo;
    List<GuoOrderGoods> orderGoods;
    private GrouponRules rules;
    private Integer linkGrouponId;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public List<User> getJoiners() {
        return joiners;
    }

    public void setJoiners(List<User> joiners) {
        this.joiners = joiners;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<GuoOrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<GuoOrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public GrouponRules getRules() {
        return rules;
    }

    public void setRules(GrouponRules rules) {
        this.rules = rules;
    }

    public Integer getLinkGrouponId() {
        return linkGrouponId;
    }

    public void setLinkGrouponId(Integer linkGrouponId) {
        this.linkGrouponId = linkGrouponId;
    }

    @Override
    public String toString() {
        return "GroupDetail{" +
                "creator=" + creator +
                ", groupon=" + groupon +
                ", joiners=" + joiners +
                ", orderInfo=" + orderInfo +
                ", orderGoods=" + orderGoods +
                ", rules=" + rules +
                ", linkGrouponId=" + linkGrouponId +
                '}';
    }
}
