package com.hufei.mapper;

import com.hufei.entity.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author:hufei
 * @CreateTime:2020-09-09
 * @Description:用户实体对应mapper
 */
//@Mapper    //如果启动类开启了@MapperScan注解，这里不需要这个注解了
public interface UserMapper extends Mapper<User> {  //整合通用mapper后实体mapper要继承通用mapper的mapper<T>接口
}
