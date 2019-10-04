package com.cskaoyan.mall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * @author 赵亚云
 */
public class OrderStatistic {
    //amount: 总价格  orders: 订单量 customers: 下单用户数, day: 日期" pcr: 单价
    double amount;
    int orders;
    int customers;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date day;
    double pcr;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public double getPcr() {
        return pcr;
    }

    public void setPcr(double pcr) {
        this.pcr = pcr;
    }
}
