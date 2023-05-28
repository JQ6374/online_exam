package com.zz.dao;


import com.alibaba.fastjson.JSONObject;
import com.zz.bean.Topic;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface TopicDao {


    /**
     * @param uId：老师Id，由老师管理题目
     * @return 题目列表
     */
    @Select("select * from topic where (u_id=#{uId} or status=1) and is_exist=1 ORDER BY create_time DESC")
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


    /**
     * 删除题目，逻辑删除
     *
     * @param tId 题目Id
     * @return 删除条数
     */
    @Update("update topic set is_exist=0 where t_id=#{tId} and is_exist=1")
    Integer delTopic(Integer tId);

    /**
     * 修改题目信息
     *
     * @param topic 实体
     * @return 修改条数
     */
    @Update("update topic set type_id=#{typeId},tag_id=#{tagId},difficulty_id=#{difficultyId}," +
            "question=#{question},answer=#{answer},status=#{status} where t_id=#{tId}")
    Integer updateTopic(Topic topic);

    /**
     * 手动组卷，根据题目ID来组
     *
     * @param tIds 题目Id
     * @return 题目列表
     */
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


    /**
     * 题目条数，用来判断约束是否成功
     *
     * @param tagId 标签Id
     * @return 不同类型的题目数量
     */
    @Select("select type_id typeId, count(1) count " +
            "from topic where tag_id = #{tagId} group by type_id;")
    ArrayList<JSONObject> typeCount(Integer tagId);

    /**
     * 根据题目类型来查询题目
     *
     * @param typeId 类型Id
     * @return 题目列表
     */
    @Select("select t_id, type_id, tag_id, difficulty_id, question, answer from topic where (u_id=#{uId} or status=1) and is_exist=1 " +
            "and type_id=#{typeId} and tag_id=#{tagId}")
    ArrayList<Topic> selectByTypeId(Integer uId, Integer typeId, Integer tagId);
}
