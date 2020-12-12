package com.hufei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author:hufei
 * @CreateTime:2020-09-03
 * @Description:springboot启动类
 */
@SpringBootApplication      //springboot启动类注解
//@MapperScan("com.hufei.dao")     //开启扫描mybatis所有业务mapper接口的包路径, 整合通用mapper后需要将这个注解注释掉
@MapperScan("com.hufei.dao")       //整合通用mapper需要注释掉mybatis官方的@MapperScan注解，开启通用mapper的@MapperScan注解
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}