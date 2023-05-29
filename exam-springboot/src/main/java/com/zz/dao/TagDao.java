package com.zz.dao;


import com.zz.bean.Tag;
import com.zz.bean.TopicType;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface TagDao {

    @Select("select tag_id, u_id, name from tag where u_id in (0,#{uId})")
    ArrayList<Tag> selectAll(Integer uId);

    @Select("select name from tag where tag_id=#{tagId}")
    String selectById(Integer tagId);

    @Insert("insert into tag values(null,#{u_id},#{name})")
    Integer addTag(Tag tag);

    @Update("UPDATE tag SET u_id = #{uId}, name = #{name} where tag_id =#{tagId};")
    Integer updateTag(Tag tag);

    @Delete("delete from tag where tag_id = #{tagId}")
    Integer deleteTag(Integer tagId);
}
