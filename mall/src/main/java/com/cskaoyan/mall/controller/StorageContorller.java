package com.cskaoyan.mall.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.config.AliyunConfig;
import com.cskaoyan.mall.service.StorageService;
import com.cskaoyan.mall.utils.FileUpload;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * author:王小凤
 */

@RestController
public class StorageContorller {

    @Autowired
    StorageService storageService;

    @Autowired
    AliyunConfig aliyunConfig;

    @Value("${mall.file.upload}")
    String filePath;

    @RequestMapping("/wx/storage/upload")
    public BaseRespVo upload(HttpServletRequest request) throws IOException {

        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile file =  req.getFile("file");

        Storage storage = FileUpload.uploadToAliyun(aliyunConfig,file);

        //Storage storage = FileUpload.uploadToLocation(file,filePath, request);

        BaseRespVo insert = storageService.insert(storage);
        return insert;
    }

    @RequestMapping("/admin/storage/create")
    @RequiresPermissions("admin:storage:create")
    public BaseRespVo create(MultipartFile file) throws IOException {
        Storage storage = FileUpload.uploadToAliyun(aliyunConfig,file);
        BaseRespVo insert = storageService.insert(storage);
        return insert;
    }


    /*@RequestMapping("/admin/storage/create")
    @RequiresPermissions("admin:storage:create")
    public BaseRespVo create(MultipartFile file, HttpServletRequest request) throws IOException {
        Storage storage = FileUpload.uploadToLocation(file,filePath, request);
        BaseRespVo insert = storageService.insert(storage);
        return insert;
    }*/
}
