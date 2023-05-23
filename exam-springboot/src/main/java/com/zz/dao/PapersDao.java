package com.zz.dao;

import com.zz.bean.Papers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


@Mapper
public interface PapersDao {

    @Insert("insert into papers values(null, #{name}, #{content},#{createTime})")
    Integer addPaper(Papers papers);

    //  获取整个papers
    @Select("select * from papers where p_id = #{pId}")
    ArrayList<Papers> getPaper(Integer pId);

    //获取papers的content
    @Select("select content from papers WHERE p_id = #{pId}")
    String getPaperContent(Integer pId);
}
