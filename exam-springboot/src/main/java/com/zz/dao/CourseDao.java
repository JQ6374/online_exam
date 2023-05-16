package com.zz.dao;

import com.zz.bean.Course;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CourseDao {

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
