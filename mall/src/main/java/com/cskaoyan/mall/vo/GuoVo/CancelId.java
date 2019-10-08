package com.cskaoyan.mall.vo.GuoVo;

public class CancelId {
    Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CancelId{" +
                "orderId=" + orderId +
                '}';
    }
}
