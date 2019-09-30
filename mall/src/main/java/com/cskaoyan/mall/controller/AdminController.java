package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 系统管理部分接口
 * @author 李锐
 */
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("admin/admin/list")
    public BaseRespVo list(@RequestParam("page")int page, @RequestParam("limit")int limit,
                           @RequestParam("sort")String sort,@RequestParam("order")String order) throws IOException {
        Map<String, Object> data =  adminService.getPageList(page, limit, sort, order);
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }

    @RequestMapping("admin/role/options")
    public BaseRespVo options() {
        List<Map<String, Object>> data = adminService.getAllRoleOptions();
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }
    @RequestMapping("admin/log/list")
    public BaseRespVo logList(@RequestParam("page")int page, @RequestParam("limit")int limit,
                              @RequestParam("sort")String sort,@RequestParam("order")String order) {
        Map<String, Object> data = adminService.getAllLogList(page, limit, sort, order);
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }
}
