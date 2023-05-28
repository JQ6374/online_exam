package com.zz.dao;

import com.zz.bean.Exam;
import com.zz.bean.Papers;
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
    @Select("select * from exam where u_id=#{uId}")
    @Results({
            @Result(property = "content", column = "p_id",
                    one = @One(select = "com.zz.dao.PapersDao.getPaperContent")
            )
    })
    public List<Exam> selectAll(Integer uId);

    //    查询ById
    @Select("select * from exam where e_id=#{eId}")
    @Results({
            @Result(property = "content", column = "p_id",
                    javaType = Papers.class,
                    one = @One(select = "com.zz.dao.PapersDao.getPaperContent")
            )
    })
    public Exam selectOne(Integer eId);
}
