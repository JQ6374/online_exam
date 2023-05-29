package com.zz.dao;


import com.zz.bean.TopicType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface TopicTypeDao {

    @Select("select type_id, name from topic_type")
    ArrayList<TopicType> selectAll();

    /**
     * 用在关联映射
     * @param typeId 类型Id
     */
    @Select("select name from topic_type where type_id=#{typeId}")
    String selectById(Integer typeId);
}
