package com.zz.dao;

import com.zz.bean.Exam;
import com.zz.bean.Papers;
import com.zz.bean.StudentExam;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
//@Repository
public interface ExamDao {
    @Transactional
    //创建考试
    @Insert("insert into exam values(null, #{uId}, #{cId},#{pId},#{name},#{startTime},#{endTime},1,1)")
    public Integer createExam(Exam exam);

    //    修改信息
    @Update("UPDATE exam set c_id=#{cId},p_id=#{pId},name=#{name},start_time = #{startTime},end_time=#{endTime} where e_id=#{eId}")
    public Integer updateExamInfo(Exam exam);

    // 考试开启

    // 注销考试 is_exist 默认状态为1 注销为0
    @Update("UPDATE exam set is_exist = 0 where e_id =#{param01}")
    public Integer deleteExam(@Param("param01") Integer examId);

    // 查询所有 All 会获取papers的内容
    @Select("select * from exam where u_id=#{uId} ORDER BY start_time DESC")
    @Results({
            @Result(property = "pId", column = "p_id"),
            @Result(property = "content", column = "p_id",
                    one = @One(select = "com.zz.dao.PapersDao.getPaperContent")
            )
    })
    public List<Exam> selectAll(Integer uId);

    //    查询ById
    @Select("select * from exam where e_id=#{eId}")
    @Results({
            @Result(property = "pId", column = "p_id"),
            @Result(property = "content", column = "p_id",
                    javaType = Papers.class,
                    one = @One(select = "com.zz.dao.PapersDao.getPaperContent")
            )
    })
    public Exam selectOne(Integer eId);

    /**
     * 存储学生考试提交记录以及试卷
     * @param studentExam
     * @return
     */
    @Insert("insert into student_exam values(null, #{uId}, #{eId}, #{rightStudentAnswer}, #{createTime})")
    public Integer addExamRecord(StudentExam studentExam);

    @Select("select count(1) from student_exam where u_id=#{uId} and e_id=#{eId}")
    public Integer selectIsExist(StudentExam studentExam);
}
