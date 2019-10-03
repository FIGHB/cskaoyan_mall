package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.service.GrouponService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrouponController {
    @Autowired
    GrouponService grouponService;
    @RequestMapping("/admin/groupon/list")
    public BaseRespVo grouponList(int page, int limit, GrouponRules grouponRules){
        BaseRespVo baseRespVo = grouponService.selectAllGroupon(page,limit, grouponRules);
        return baseRespVo;
    }

    @RequestMapping("/admin/groupon/update")
    public BaseRespVo update(@RequestBody GrouponRules grouponRules){
        BaseRespVo update = grouponService.update(grouponRules);
        return update;
    }

    @RequestMapping("/admin/groupon/delete")
    public BaseRespVo delete(@RequestBody GrouponRules grouponRules){
        BaseRespVo delete = grouponService.delete(grouponRules);
        return delete;
    }

    @RequestMapping("/admin/groupon/create")
    public BaseRespVo create(@RequestBody GrouponRules grouponRules){
        BaseRespVo create = grouponService.create(grouponRules);
        return create;
    }

    @RequestMapping("/admin/groupon/listRecord")
    public BaseRespVo listRecord(int page,int limit,GrouponRules grouponRules){
        BaseRespVo baseRespVo = grouponService.listRecord(page,limit,grouponRules);
        return baseRespVo;
    }
}
