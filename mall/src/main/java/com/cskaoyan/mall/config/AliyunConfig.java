package com.cskaoyan.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.xml.soap.SAAJResult;

@Component
@ConfigurationProperties(prefix = "mall.aliyun")
public class AliyunConfig {
    String accessKeyId;
    String accessSecret;
    String url;

    @Autowired
    MallOssConfig ossConfig;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MallOssConfig getOssConfig() {
        return ossConfig;
    }

    public void setOssConfig(MallOssConfig ossConfig) {
        this.ossConfig = ossConfig;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }
}
