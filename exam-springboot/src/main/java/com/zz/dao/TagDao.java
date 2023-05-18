package com.zz.dao;


import com.zz.bean.Tag;
import com.zz.bean.TopicType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface TagDao {

    @Select("select tag_id, u_id, name from tag where u_id in (0,#{uId})")
    ArrayList<Tag> selectAll(Integer uId);

    @Select("select name from tag where tag_id=#{tagId}")
    String selectById(Integer tagId);
}
