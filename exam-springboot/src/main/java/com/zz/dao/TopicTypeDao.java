package com.zz.dao;


import com.zz.bean.TopicType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface TopicTypeDao {

    @Select("select type_id, name from topic_type")
    ArrayList<TopicType> selectAll();

    @Select("select name from topic_type where type_id=#{typeId}")
    String selectById(Integer typeId);
}
