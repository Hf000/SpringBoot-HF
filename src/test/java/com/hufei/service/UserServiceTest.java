package com.hufei.service;

import com.hufei.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author:hufei
 * @CreateTime:2020-09-11
 * @Description:
 */
@RunWith(SpringRunner.class)        //开启测试环境依赖spring容器
@SpringBootTest                     //开启springboot项目测试注解
public class UserServiceTest {

    @Autowired
    private IUserService userServiceImpl;

    @Test
    public void findUserInfo() {
        User user = userServiceImpl.findUserInfo(8l);
        System.out.println("user信息 = "+user);
    }

    @Test
    public void saveUserInfo() {
        User user = userServiceImpl.saveUserInfo("test7","123456", "test7", 10, 1, new Date(),
                null, null, null);
        System.out.println("新增user信息 = "+user);
    }

}