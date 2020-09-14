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
//@MapperScan("com.hufei.mapper")     //开启扫描mybatis所有业务mapper接口的包路径, 整合通用mapper后需要将这个注解注释掉
@MapperScan("com.hufei.mapper")       //整合通用mapper需要注释掉mybatis官方的@MapperScan注解，开启通用mapper的@MapperScan注解
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/*
 *  ctrl + shift + alt + s 开启当前项目的结构体structure
 *
 *  ctrl + shift + t 创建类的测试方法
 *  ctrl + shift + n 快捷键 根据文件名称查找
 *
 *  ctrl + alt + b 从接口方法跳转到实现类方法
 *  ctrl + alt + s 快捷打开idea的setting窗口
 *  ctrl + alt + l 格式化代码
 *
 *  ctrl + y 删除当前行
 *  ctrl + d 向下复制当前行
 *  ctrl + enter 向下插入空行
 *  ctrl + / 注释当前行并移动到下一行的位置
 *  ctrl + i 实现父类接口的方法
 *  ctrl + o 实现所有可重写方法
 *  ctrl + h 查看接口的实现类
 *
 *  alt + insert 生成get/set/toString方法
 *  alt + enter 万能快捷键
 *  alt + / 代码自动提示快捷键
 *
 * */