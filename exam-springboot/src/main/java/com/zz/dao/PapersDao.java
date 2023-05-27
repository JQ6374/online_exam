package com.zz.dao;

import com.zz.bean.Papers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.awt.print.Paper;
import java.util.ArrayList;


@Mapper
public interface PapersDao {

    @Select("select * from papers where u_id=#{uId} and is_exist=1")
    ArrayList<Papers> selectAllByUId(Integer uId);

    /**
     * 添加试卷
     *
     * @param papers 试卷对象
     */
    @Insert("insert into papers values(null,#{uId}, #{name}, #{content}, #{createTime},1)")
    Integer addPaper(Papers papers);

    /**
     * 选择试卷
     *
     * @param pId 试卷Id
     */
    @Select("select * from papers where p_id = #{pId} and is_exist=1")
    ArrayList<Papers> getPaper(Integer pId);

    /**
     * 获取papers的content
     *
     * @param pId 试卷Id
     */
    @Select("select content from papers WHERE p_id = #{pId} and is_exist=1")
    String getPaperContent(Integer pId);

    /**
     * 删除试卷，逻辑删除
     *
     * @param pId 试卷Id
     */
    @Update("update papers set is_exist=0 where p_id=#{pId}")
    Integer deleteByPId(Integer pId);

    /**
     * 修改试卷名称
     *
     * @param pId  试卷Id
     * @param name 要修改的名称
     */
    @Update("update papers set name=#{name} where p_id=#{pId} and is_exist=1")
    Integer updateName(Integer pId, String name);
}
