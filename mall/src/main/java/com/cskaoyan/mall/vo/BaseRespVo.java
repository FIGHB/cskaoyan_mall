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

    public static BaseRespVo err(Object data){
        BaseRespVo<Object> objectLoginVo = new BaseRespVo<>();
        objectLoginVo.setData(data);
        objectLoginVo.setErrmsg("失败,用户id重复");
        objectLoginVo.setErrno(1);
        return objectLoginVo;
    }
    /*ok和getBaseResVo功能差不多，建议使用后者，因为不一定成功*/
    public static BaseRespVo getBaseResVo(int errno, Object data, String errmsg) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(errno);
        if(data != null) baseRespVo.setData(data);
        if(errmsg != null)baseRespVo.setErrmsg(errmsg);
        return baseRespVo;
    }

    public static BaseRespVo fail(int errno,  String errmsg) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(errno);
        if(errmsg != null)baseRespVo.setErrmsg(errmsg);
        return baseRespVo;

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
