package com.hufei.service;

import com.hufei.entity.User;

import java.util.Date;

/**
 * @Author:hufei
 * @CreateTime:2020-09-09
 * @Description:用户信息处理业务接口
 */
public interface IUserService {

    User findUserInfo(Long id);

    User saveUserInfo(String userName, String password, String name, Integer age, Integer sex, Date birthday,
                      String note, Date created, Date updated);

}
