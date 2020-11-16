package com.hufei.controller;

import com.hufei.entity.User;
import com.hufei.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/**
 * @Author:hufei
 * @CreateTime:2020-09-09
 * @Description:用户控制处理器
 */
@RestController
public class UserController {

//    @Autowired
//    private DataSource dataSource;    //单数据源注入
//    @Resource(name = "myRoutingDataSource")
//    private DataSource myRoutingDataSource;     //多数据源注入,获取的是默认数据源

    @Autowired
    private IUserService userServiceImpl;

    @GetMapping("/find/{id}")
    public User getUserInfo(@PathVariable Long id) {
        if (id == null) id = 0l;
        User user = userServiceImpl.findUserInfo(id);
        return user;
    }

    @RequestMapping("/save")
    public User saveUserInfo(String userName, String password, String name, Integer age, Integer sex, Date birthday,
                               String note, Date created, Date updated) {
        User user = userServiceImpl.saveUserInfo(userName, password, name, age, sex, birthday, note, created, updated);
        return user;
    }

    @GetMapping("/findUser/{id}")
    public User findUserInfo(@PathVariable Long id) {
        if (id == null) id = 0l;
        User user = userServiceImpl.getUser(id);
        return user;
    }

    @GetMapping("/findUserAll")
    public List<User> findUserInfo() {
        List<User> users = userServiceImpl.selectAll();//userServiceImpl.getUserAll();
        return users;
    }

}
