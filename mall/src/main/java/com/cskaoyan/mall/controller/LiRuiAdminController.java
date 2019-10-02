package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.LiRuiAdminService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.List_AdminVo;
import com.cskaoyan.mall.vo.PermissionsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
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
public class LiRuiAdminController {

    @Autowired
    LiRuiAdminService adminService;

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

    @RequestMapping("admin/role/list")
    public BaseRespVo getRoleList(@RequestParam("page")int page, @RequestParam("limit")int limit,
                                  @RequestParam("sort")String sort,@RequestParam("order")String order, String name) {
        Map<String, Object> data =  adminService.getRoleList(page, limit, sort, order, name);
        return BaseRespVo.ok(data);
    }



    @RequestMapping("admin/log/list")
    public BaseRespVo logList(@RequestParam("page")int page, @RequestParam("limit")int limit,
                              @RequestParam("sort")String sort,@RequestParam("order")String order, String name) {
        Map<String, Object> data = adminService.getAllLogList(page, limit, sort, order, name);
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }

    /**
     * 头像上传,此处是有最大上传图像大小设置的，所以较大的图片会上传失败
     * @return
     */
    //@RequestMapping("admin/storage/create")
    public BaseRespVo avatorUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()) {
            return BaseRespVo.getBaseResVo(0, null, "抱歉图片上传失败");
        }/* else if(file.getSize() >= 1048576) {
            return BaseRespVo.getBaseResVo(0, null, "抱歉图片过大无法上传");
        }*/
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

    @RequestMapping("admin/role/create")
    public BaseRespVo addRole(@RequestBody Role role) {
        if(adminService.addRole(role)) {
            return BaseRespVo.ok(role);
        } else {
            return BaseRespVo.getBaseResVo(5000, null, "添加失败");
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

    @RequestMapping("admin/role/update")
    public BaseRespVo updateRole(@RequestBody Role role) {
        if(adminService.updateRole(role)) {
            return BaseRespVo.ok(role);
        } else {
            return BaseRespVo.getBaseResVo(5000, null, "更新失败");
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

    @RequestMapping("admin/role/delete")
    public BaseRespVo deleteRole(@RequestBody Role role) {
        if(role.getName().equals("超级管理员"))
            return BaseRespVo.getBaseResVo(5000, null, "抱歉超级管理员一角不可删除！！！");
        if(adminService.deleteRoleById(role.getId())) {
            return BaseRespVo.ok(null);
        } else {
            return BaseRespVo.getBaseResVo(5000, null, "失败");
        }
    }

    //关于 permissions 查询的还没有添加 systemPermissions 部分的数据，
    //需要在 SystemPermissionUtils 中实现
    @RequestMapping(value = "admin/role/permissions", method = RequestMethod.GET)
    public BaseRespVo rolePermissions(@RequestParam String roleId) {
        Map<String, Object> data = adminService.getPermissionList(roleId);
        return BaseRespVo.ok(data);
    }

    @RequestMapping(value = "admin/role/permissions", method = RequestMethod.POST)
    public BaseRespVo addPermissions(@RequestBody PermissionsVo  permissionsVo) {
        if(permissionsVo != null && adminService.addPermissions(permissionsVo.getRoleId(),permissionsVo.getPeimissions())) {
            return BaseRespVo.ok("");
        } else {
            return BaseRespVo.getBaseResVo(500,null,"添加失败");
        }
    }

    //对象存储部分
    @RequestMapping("admin/storage/list")
    public BaseRespVo getStorageList(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                     @RequestParam("sort") String sort, @RequestParam("order") String order,
                                     String key, String name) {
        Map<String, Object> data = adminService.getStorageList(page, limit, sort, order, key, name);
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }

    @RequestMapping("admin/storage/update")
    public BaseRespVo updateStorage(@RequestBody Storage storage) {
        if(adminService.updateStorage(storage)) {
            return BaseRespVo.ok(storage);
        } else {
            return BaseRespVo.getBaseResVo(5000, null, "更新失败");
        }
    }

    @RequestMapping("admin/storage/delete")
    public BaseRespVo deleteStorage(@RequestBody Storage storage) {
        if(adminService.deleteStorageById(storage.getId())) {
            return BaseRespVo.ok(null);
        } else {
            return BaseRespVo.getBaseResVo(5000, null, "删除失败");
        }
    }

}

