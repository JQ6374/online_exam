package com.zz.dao;

import com.zz.bean.Exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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
}
