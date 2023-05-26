package com.zz.dao;

import com.alibaba.fastjson.JSONObject;
import com.zz.bean.Course;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CourseDao {

    @Select("select * from course where u_id=#{uId} and is_exist=1")
    ArrayList<Course> selectByUId(Integer uId);


    @Select("select sc.u_id,`user`.username,`user`.email,course.`name` FROM `user`,course,student_course as sc\n" +
            "WHERE  `user`.u_id = sc.u_id  AND sc.c_id = course.c_id AND course.u_id = #{uId}")
    ArrayList<JSONObject> selectStudentAndCourse(Integer uId);


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

}
