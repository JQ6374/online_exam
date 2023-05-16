package com.zz.dao;

import com.zz.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@Repository
public interface UserDao {

    @Insert("insert into user values(null, #{rId}, " +
            "#{username}, #{password}, #{email}, #{registerTime})")
    Integer addUser(User user);

}
