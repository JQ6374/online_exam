package com.zz.dao;

import com.alibaba.fastjson.JSONObject;
import com.zz.bean.Course;
import com.zz.bean.Exam;
import com.zz.bean.Papers;
import com.zz.bean.StudentExam;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Mapper
//@Repository
public interface ExamDao {
    @Transactional
    //创建考试
    @Insert("insert into exam values(null, #{uId}, #{cId},#{pId},#{name},#{startTime},#{endTime},1,1)")
    public Integer createExam(Exam exam);

    //    修改信息
    @Update("UPDATE exam set u_id=#{uId},c_id=#{cId},p_id=#{pId},name=#{name},start_time = #{startTime}," + "end_time=#{endTime},status=#{status},is_exist = #{isExist} " + "where e_id=#{eId}")
    public Integer updateExamInfo(Exam exam);

    // 考试开启

    // 注销考试 is_exist 默认状态为1 注销为0
    @Update("UPDATE exam set is_exist = 0 where e_id =#{examId}")
    public Integer deleteExam(Integer examId);

    /**
     * 根据教师id查询与其相关的所有考试信息
     *
     * @param uId 教师的uid
     * @return
     */
    // 查询所有 All 会获取papers的内容
    @Select("select * from exam where u_id=#{uId} and is_exist=1 ORDER BY start_time DESC")
    @Results({@Result(property = "uId", column = "u_id"), @Result(property = "teacherName", column = "u_id", javaType = Course.class, one = @One(select = "com.zz.dao.UserDao.getNameByid")), @Result(property = "cId", column = "c_id"), @Result(property = "courseName", column = "c_id", javaType = Course.class, one = @One(select = "com.zz.dao.CourseDao.selectNameByCid")), @Result(property = "pId", column = "p_id"), @Result(property = "content", column = "p_id", one = @One(select = "com.zz.dao.PapersDao.getPaperContent"))})
    public List<Exam> selectAll(Integer uId);


    /**
     * 根据eid查询整个考试
     *
     * @param eId
     * @return
     */
    @Select("select * from exam where e_id=#{eId}")
    @Results({@Result(property = "uId", column = "u_id"),
            @Result(property = "teacherName", column = "u_id",
                    javaType = Course.class,
                    one = @One(select = "com.zz.dao.UserDao.getNameByid")),
            @Result(property = "cId", column = "c_id"),
            @Result(property = "courseName", column = "c_id", javaType = Course.class,
                    one = @One(select = "com.zz.dao.CourseDao.selectNameByCid")),
            @Result(property = "pId", column = "p_id"),
            @Result(property = "content", column = "p_id", javaType = Papers.class,
                    one = @One(select = "com.zz.dao.PapersDao.getPaperContent"))})
    public Exam selectOne(Integer eId);

    /**
     * 根据学生的uid查询这个学生的考试列表
     * 这个方法查询学生的所有课程
     *
     * @param uId
     * @return
     */
    @Select("SELECT *,c.name as courName FROM course c "
            + "JOIN student_course sc "
            + "ON c.c_id = sc.c_id "
            + "JOIN user s ON sc.u_id = s.u_id "
            + "WHERE s.u_id = #{uId}")
    List<Course> getCoursesByUid(Integer uId);

    /**
     * 根据方法getCoursesByUid的结果集进行查询，既根据学生的所属课程进行查询该学生所有的考试信息
     * @param cIds getCoursesByUid的结果集元素
     * @return
     */
    @Select("<script>" +
            "SELECT e.*, c.name as courseName, u.username as teacherName FROM exam e " +
            "JOIN course c ON e.c_id = c.c_id " +
            "JOIN user u ON c.u_id = u.u_id " +
            "<where>" +
            "<choose>" +
            "<when test='cIds == null or cIds.size() == 0'>" +
            "and c.c_id in (-1)" +
            "</when>" +
            "<otherwise>" +
            "and c.c_id in " +
            "<foreach item='cId' collection='cIds' open='(' separator=',' close=')'>" +
            "#{cId}" +
            "</foreach>" +
            "</otherwise>" +
            "</choose>" +
            "and e.is_exist = 1" +
            "</where>" +
            "order by start_time desc" +
            "</script>")
    ArrayList<JSONObject> getExamsByCourseId(ArrayList<Integer> cIds);

    /**
     * 存储学生考试提交记录以及试卷
     *
     * @param studentExam
     * @return
     */
    @Insert("insert into student_exam values(null, #{uId}, #{eId}, #{rightStudentAnswer}, #{createTime})")
    public Integer addExamRecord(StudentExam studentExam);

    @Select("select count(1) from student_exam where u_id=#{uId} and e_id=#{eId}")
    public Integer selectIsExist(StudentExam studentExam);

    /**
     * 查询所有的考试，来更新状态
     */
    @Select("select * from exam")
    public ArrayList<Exam> findAll();

    @Update("update exam set status=#{status} where e_id=#{eId}")
    public Integer updateStatus(Exam exam);

    @Select("select count(1) from student_exam where u_id=#{uId} and e_id=#{eId}")
    Integer isSubmit(Integer uId, Integer eId);

    @Select("select * from student_exam where u_id=#{uId}")
    ArrayList<JSONObject> submitList(Integer uId);
}
