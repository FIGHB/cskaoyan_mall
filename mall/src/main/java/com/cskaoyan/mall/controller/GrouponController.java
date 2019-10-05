package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.service.GrouponService;
import com.cskaoyan.mall.utils.GetString;
import com.cskaoyan.mall.utils.IsNumber;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrouponController {
    @Autowired
    GrouponService grouponService;
    @RequestMapping("/admin/groupon/list")
    @RequiresPermissions("admin:groupon:list")
    public BaseRespVo grouponList(int page, int limit, GetString getString){
        GrouponRules grouponRules = new GrouponRules();
        String goodsId = getString.getGoodsId();
        if(goodsId!=null&&goodsId!=""){
            if(goodsId.length()>10){
                return BaseRespVo.fail(401, "参数不对");
            }
            if(IsNumber.isNumber(goodsId)) {
                grouponRules.setGoodsId(Integer.parseInt(goodsId));
            }else {
                return BaseRespVo.fail(401, "参数不对");
            }
        }
        BaseRespVo baseRespVo = grouponService.selectAllGroupon(page,limit, grouponRules);
        return baseRespVo;
    }

    @RequestMapping("/admin/groupon/update")
    @RequiresPermissions("admin:groupon:update")
    public BaseRespVo update(@RequestBody GrouponRules grouponRules){
        BaseRespVo update = grouponService.update(grouponRules);
        return update;
    }

    @RequestMapping("/admin/groupon/delete")
    @RequiresPermissions("admin:groupon:delete")
    public BaseRespVo delete(@RequestBody GrouponRules grouponRules){
        BaseRespVo delete = grouponService.delete(grouponRules);
        return delete;
    }

    @RequestMapping("/admin/groupon/create")
    @RequiresPermissions("admin:groupon:create")
    public BaseRespVo create(@RequestBody GrouponRules grouponRules){
        BaseRespVo create = grouponService.create(grouponRules);
        return create;
    }

    @RequestMapping("/admin/groupon/listRecord")
    @RequiresPermissions("admin:groupon:listRecord")
    public BaseRespVo listRecord(int page,int limit,GetString getString){
        GrouponRules grouponRules = new GrouponRules();
        String goodsId = getString.getGoodsId();
        if(goodsId!=null&&goodsId!=""){
            if(goodsId.length()>10){
                return BaseRespVo.fail(401, "参数不对");
            }
            if(IsNumber.isNumber(goodsId)) {
                grouponRules.setGoodsId(Integer.parseInt(goodsId));
            }else {
                return BaseRespVo.fail(401, "参数不对");
            }
        }
        BaseRespVo baseRespVo = grouponService.listRecord(page,limit,grouponRules);
        return baseRespVo;
    }
}
