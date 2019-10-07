package com.cskaoyan.mall.vo.GuoVo;

public class HandleOption {
    boolean cancel=false;
    boolean delete=true;
    boolean pay=false;
    boolean comment=false;
    boolean confirm=false;
    boolean refund=false;
    boolean rebuy=false;

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean isRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }

    public boolean isRebuy() {
        return rebuy;
    }

    public void setRebuy(boolean rebuy) {
        this.rebuy = rebuy;
    }

    @Override
    public String toString() {
        return "HandleOption{" +
                "cancel=" + cancel +
                ", delete=" + delete +
                ", pay=" + pay +
                ", comment=" + comment +
                ", confirm=" + confirm +
                ", refund=" + refund +
                ", rebuy=" + rebuy +
                '}';
    }
}
