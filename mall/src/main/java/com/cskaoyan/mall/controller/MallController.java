package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.MallService;
import com.cskaoyan.mall.utils.ListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class MallController {
    @Autowired
    MallService mallService;
    /**
     * 周榆淮
     * 查询行政规划
     */
    @RequestMapping("region/list")
    public BaseRespVo queryRegionList(){
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        objectBaseRespVo.setData(mallService.queryRegionList());
        return objectBaseRespVo;
    }

}
