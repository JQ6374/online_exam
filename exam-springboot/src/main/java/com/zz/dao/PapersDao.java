package com.zz.dao;

import com.zz.bean.Papers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface PapersDao {

    @Insert("insert into papers values(null, #{name}, #{content},#{createTime})")
    Integer addPaper(Papers papers);

    //  获取整个papers
    @Select("select * from papers WHERE p_id = #{param01}")
    Papers getPaper(@Param("param01") Integer pId);

    //获取papers的content
    @Select("select content from papers WHERE p_id = #{param01}")
    String getPaperContent(@Param("param01") Integer pId);
}
