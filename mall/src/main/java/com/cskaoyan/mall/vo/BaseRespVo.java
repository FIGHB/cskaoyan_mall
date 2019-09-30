package com.cskaoyan.mall.vo;

public class BaseRespVo<T> {
    private T data;
    private String errmsg;
    private int errno;

    public static BaseRespVo ok(Object data) {
        BaseRespVo<Object> objectLoginVo = new BaseRespVo<>();
        objectLoginVo.setData(data);
        objectLoginVo.setErrmsg("成功");
        objectLoginVo.setErrno(0);
        return objectLoginVo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }
}
