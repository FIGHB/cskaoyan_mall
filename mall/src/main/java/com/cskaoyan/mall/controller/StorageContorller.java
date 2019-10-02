package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.StorageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    @RequestMapping("/admin/storage/create")
    public BaseRespVo create(MultipartFile file, HttpServletRequest request) throws IOException {
        String url="/wx/storage/fetch/";
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        String realPath=path.getAbsolutePath()+"/static"+url;
        System.out.println(realPath);
        String key = UUID.randomUUID().toString()+".jpg";
        File file1 = new File(realPath+key);
        if(!file1.exists()){
            file1.mkdirs();
        }
        file.transferTo(file1);
        Storage storage = new Storage();
        storage.setKey(key);
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        requestURL = requestURL.replace(requestURI,"");
        requestURL = requestURL+url+key;
        storage.setUrl(requestURL);
        storage.setType(file.getContentType());
        storage.setSize((int)file.getSize());
        storage.setName(file.getOriginalFilename());
        Date date = new Date();
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        BaseRespVo insert = storageService.insert(storage);
        return insert;
    }
}
