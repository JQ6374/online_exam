package com.zz.dao;


import com.zz.bean.Topic;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface TopicDao {


    /**
     * @param uId：老师Id，由老师管理题目
     * @return 题目列表
     */
    @Select("select * from topic where (u_id=#{uId} or status=1) and is_exist=1")
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

    /**
     * 添加题目
     *
     * @param topic：题目
     * @return 添加条数
     */
    @Insert("insert into topic values(null,#{uId},#{typeId},#{tagId}," +
            "#{difficultyId},#{question},#{answer},#{status},1,#{createTime})")
    Integer addTopic(Topic topic);


    @Update("update topic set is_exist=0 where t_id=#{tId} and is_exist=1")
    Integer delTopic(Integer tId);

    @Update("update topic set type_id=#{typeId},tag_id=#{tagId},difficulty_id=#{difficultyId}," +
            "question=#{question},answer=#{answer},status=#{status} where t_id=#{tId}")
    Integer updateTopic(Topic topic);

    @Select({
            "<script>",
            "select * from topic",
            "<where>",
            "t_id in",
            "<foreach item='tId' collection='tIds' open='(' separator=',' close=')'>",
            "#{tId}",
            "</foreach>",
            "</where>",
            "</script>"
    })
    ArrayList<Topic> topicToPapers(Integer[] tIds);
}
