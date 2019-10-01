package com.cskaoyan.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.cskaoyan.mall.mapper")
@SpringBootApplication
<<<<<<< HEAD
=======
@MapperScan(basePackages = "com.cskaoyan.mall.mapper")
>>>>>>> 9d17a7f0bc2543dfe454abe8bbd2984b44557318
@EnableTransactionManagement
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }
}
