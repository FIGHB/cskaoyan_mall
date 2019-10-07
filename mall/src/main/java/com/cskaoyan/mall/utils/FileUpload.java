package com.cskaoyan.mall.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.config.AliyunConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;
@Controller
public class FileUpload {



    public static Storage uploadToAliyun(AliyunConfig aliyunConfig,MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String accessKeyId = aliyunConfig.getAccessKeyId();
        String accessSecret = aliyunConfig.getAccessSecret();
        String bucket = aliyunConfig.getOssConfig().getBucket();
        String endPoint = aliyunConfig.getOssConfig().getEndPoint();
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessSecret);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String key = uuid + ".jpg";
        ossClient.putObject(new PutObjectRequest(bucket,key, inputStream));
        String requestURL = aliyunConfig.getUrl()+key;
        Storage storage = getStorage(file, requestURL, key);
        return storage;
    }
    private static Storage getStorage(MultipartFile file,String requestURL,String key){
        Storage storage = new Storage();
        storage.setKey(key);
        storage.setUrl(requestURL);
        storage.setType(file.getContentType());
        storage.setSize((int)file.getSize());
        storage.setName(file.getOriginalFilename());
        Date date = new Date();
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        return storage;
    }

    public static Storage uploadToLocation(MultipartFile file, String filePath,HttpServletRequest request) throws IOException {
        String url="/wx/storage/fetch/";
        String key = UUID.randomUUID().toString()+".jpg";
        File file1 = new File(filePath+url+key);
        if(!file1.exists()){
            file1.mkdirs();
        }
        file.transferTo(file1);
        String requestURL = request.getRequestURL().toString();
        String requestURI = request.getRequestURI();
        requestURL = requestURL.replace(requestURI,"");
        requestURL = requestURL+url+key;
        Storage storage = getStorage(file, requestURL, key);
        return storage;
    }
}
