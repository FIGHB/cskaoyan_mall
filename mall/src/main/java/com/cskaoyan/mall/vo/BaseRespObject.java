package com.cskaoyan.mall.vo;

public class BaseRespObject {
    private BaseRespVo baseRespVo;
    private Object object;

    public BaseRespObject() {
    }

    public BaseRespObject(BaseRespVo baseRespVo, Object object) {
        this.baseRespVo = baseRespVo;
        this.object = object;
    }

    public BaseRespVo getBaseRespVo() {
        return baseRespVo;
    }

    public void setBaseRespVo(BaseRespVo baseRespVo) {
        this.baseRespVo = baseRespVo;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
