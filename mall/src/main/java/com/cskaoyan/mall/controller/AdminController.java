package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.List_AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
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
                           @RequestParam("sort")String sort,@RequestParam("order")String order, String username) throws IOException {
        Map<String, Object> data =  adminService.getPageList(page, limit, sort, order, username);
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

    /**
     * 头像上传
     * @return
     */
    @RequestMapping("admin/storage/create")
    public BaseRespVo avatorUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()) {
            return BaseRespVo.getBaseResVo(0, null, "抱歉文件上传失败");
        }
        //获取项目根目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        //获取文件保存的目录
        File upload = new File(path.getAbsolutePath(),"static/images/upload/");
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
        String fileName = file.getOriginalFilename();
        //文件的绝对路径
        File dest = new File(upload + "/" +  fileName);
        file.transferTo(dest);
        //url
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        requestURL = requestURL.replace(requestURI, "");
        requestURL = requestURL + "/images/upload/" + fileName;
        Storage storage = adminService.avatorUpload(upload, fileName, dest.length(), requestURL);
        return BaseRespVo.ok(storage);
    }

    @RequestMapping("admin/admin/create")
    public BaseRespVo addAdmin(@RequestBody List_AdminVo adminVo) {
        adminVo.setStrRoleIds(Arrays.toString(adminVo.getRoleIds()));
        if(adminService.addAdmin(adminVo)) {
            return BaseRespVo.ok(adminVo);
        } else {
            return BaseRespVo.getBaseResVo(5000, null, "失败");
        }
    }

    @RequestMapping("admin/admin/update")
    public BaseRespVo updateAdmin(@RequestBody List_AdminVo adminVo) {
        adminVo.setStrRoleIds(Arrays.toString(adminVo.getRoleIds()));
        if(adminService.updateAdmin(adminVo)) {
            return BaseRespVo.ok(adminVo);
        } else {
            return BaseRespVo.getBaseResVo(5000, null, "失败");
        }
    }

    /**
     * 删除指定用户
     * @param adminVo
     * @return
     */
    @RequestMapping("admin/admin/delete")
    public BaseRespVo deleteAdmin(@RequestBody List_AdminVo adminVo) {
        if(adminVo.getUsername().equals("admin")) {
            return BaseRespVo.getBaseResVo(5000, null, "抱歉该用户为超级管理员不可删除");
        }
        Integer[] roleIds = adminVo.getRoleIds();
        for (int i = 0; i < roleIds.length; i++) {
            if(roleIds[i] == 1) {
                return BaseRespVo.getBaseResVo(5000, null, "抱歉该用户为超级管理员,不可删除");
            }
        }
        if(adminService.deleteAdminById(adminVo.getId())) {
            return BaseRespVo.ok(null);
        } else {
            return BaseRespVo.getBaseResVo(5000, null, "失败");
        }
    }
}

