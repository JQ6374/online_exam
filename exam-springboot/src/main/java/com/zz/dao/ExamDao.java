package com.zz.dao;

import com.zz.bean.Exam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
//@Repository
public interface ExamDao {
    //创建考试
    @Insert("insert into exam values(null, #{c_id}, " +
            "#{name}, #{start_time}, #{end_time}, #{status})")
    Integer createExam(Exam exam);

}
