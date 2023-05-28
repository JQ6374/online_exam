package com.zz.dao;

import com.alibaba.fastjson.JSONObject;
import com.zz.bean.Course;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CourseDao {

    @Select("select * from course where u_id= #{uId} and is_exist=1 ORDER BY create_time DESC")
    ArrayList<Course> selectByUId(Integer uId);

    /**
     * 查询该老师所教课程的所有学生
     *
     * @param uId 老师Id
     * @return
     */
    @Select("select sc.u_id as uId, course.c_id as cId, sc.uc_id as ucId," +
            "user.userName, user.email, " +
            "course.name as courseName " +
            "FROM user,course,student_course as sc " +
            "WHERE user.u_id = sc.u_id  AND sc.c_id = course.c_id AND course.u_id = #{uId} ORDER BY create_time DESC")
    ArrayList<JSONObject> selectStudentAndCourse(Integer uId);

    @Update("update student_course set c_id=#{cId} where uc_id = #{ucId}")
    Integer updateStudentByCourse(Integer cId, Integer ucId);

    @Insert("insert into course values(null,#{uId},#{name},#{courseCode},#{createTime},1)")
    boolean addCourse(Course course);

    @Update("update course set is_exist=0 where c_id=#{cId}")
    boolean deleteCourse(Integer cId);

    @Update("update course set name=#{name} where c_id=#{cId}")
    boolean updateName(Course course);

    @Select("select c_id from course where is_exist=1 and course_code=#{code}")
    Integer selectByCode(String code);

    @Insert("insert into student_course values(null, #{uId}, #{cId})")
    boolean joinCourse(Integer uId, Integer cId);

    @Select("select count(1) from student_course where u_id=#{uId} and c_id=#{cId}")
    Integer isJoin(Integer uId, Integer cId);

    @Update("delete from student_course where uc_id=#{ucId}")
    Integer deleteStudentByCourse(Integer ucId);
}
