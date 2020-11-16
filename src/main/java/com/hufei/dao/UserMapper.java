package com.hufei.dao;

import com.hufei.entity.User;
import com.hufei.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:hufei
 * @CreateTime:2020-09-09
 * @Description:用户实体对应mapper
 */
//@Mapper    //如果启动类开启了@MapperScan注解，这里不需要这个注解了
@Repository //这个注解可加可不加
public interface UserMapper extends BaseMapper<User> {//BaseMapper继承通用Mapper<T>和MySqlMapper<T>
    User getUser(long id);

    List<User> getUserAll();
}
