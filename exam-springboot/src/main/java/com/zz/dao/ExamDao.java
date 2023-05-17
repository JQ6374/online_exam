package com.zz.dao;

import com.zz.bean.Exam;
import com.zz.bean.Papers;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
//@Repository
public interface ExamDao {
    //创建考试
    @Insert("insert into exam values(null, #{cId}, " +
            "#{name}, #{startTime}, #{endTime}, #{status})")
    public Integer createExam(Exam exam);


    //注销考试 is_exist 默认状态为1 注销为0
    @Update("UPDATE exam set is_exist = 0 where e_id =#{param01}")
    public Integer deleteExam(@Param("param01") Integer examId);

//    查询所有 All 会获取papers的内容
    @Select("select * from exam")
    @Results(
            {
                    @Result(property = "content",column = "p_id",
                            javaType = Papers.class,
                            one = @One(select = "com.zz.dao.PaperDao.getPaperContent")
                    )
            }
    )
    public List<Exam> selectAll();


//    查询ById
    @Select("select * from exam where e_id=#{param01}")
    @Results(
            {
                    @Result(property = "content",column = "p_id",
                            javaType = Papers.class,
                            one = @One(select = "com.zz.dao.PaperDao.getPaperContent")
                    )
            }
    )
    public Exam selectOne(@Param("param01")Integer examId);
}
