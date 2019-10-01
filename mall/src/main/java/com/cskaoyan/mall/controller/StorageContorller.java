package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.StorageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class StorageContorller {
    @Autowired
    StorageService storageService;
    @RequestMapping("/admin/storage/create")
    public BaseRespVo create(MultipartFile file, HttpServletRequest request) throws IOException {
        String url="wx/storage/fetch";
        String realPath=request.getServletContext().getRealPath(url);
        System.out.println(realPath);
        String key = UUID.randomUUID().toString()+".jpg";
        File file1 = new File(realPath+key);
        file.transferTo(file1);
        Storage storage = new Storage();
        storage.setKey(key);
        storage.setUrl(url+"/"+key);
        storage.setType(file.getContentType());
        storage.setSize((int)file.getSize());
        storage.setName(file.getOriginalFilename());
        BaseRespVo insert = storageService.insert(storage);
        return insert;
    }
}
