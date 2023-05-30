package com.zz.dao;

import com.zz.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
//@Repository
public interface UserDao {

    @Insert("insert into user values(null, #{rId}, " +
            "#{username}, #{password}, #{email}, #{registerTime})")
    Integer addUser(User user);

    @Select("select * from user where email=#{email} and r_id=#{rId}")
    ArrayList<User> selectByEmail(User user);

    @Update("update user set password=#{password} where email=#{email} and r_id=#{rId}")
    Integer updateUser(User user);

    @Select("select username from user where u_id = #{uId}")
    String getNameByid(Integer uId);
}
