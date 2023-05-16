package com.zz.dao;


import com.zz.bean.Topic;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface TopicDao {


    @Select("select * from topic where u_id=#{uId} or status=1")
    @Results({
            @Result(column = "type_id", property = "typeId"),
            @Result(column = "type_id", property = "typeName",
            one = @One(select = "com.zz.dao.TopicTypeDao.selectById")),
            @Result(column = "tag_id", property = "tagId"),
            @Result(column = "tag_id", property = "tagName",
            one = @One(select = "com.zz.dao.TagDao.selectById")),
            @Result(column = "difficulty_id", property = "difficultyId"),
            @Result(column = "difficulty_id", property = "difficultyName",
            one = @One(select = "com.zz.dao.DifficultyDao.selectById"))
    })
    ArrayList<Topic> selectByUId(Integer uId);

    @Insert("insert into topic values(null,#{uId},#{typeId},#{tagId}," +
            "#{difficultyId},#{question},#{answer},#{status},#{createTime})")
    Integer addTopic(Topic topic);
}
