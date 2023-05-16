package com.zz.dao;

import com.zz.bean.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
public interface CourseDao {

    @Insert("insert into course values(null,#{uId},#{name},#{courseCode},#{createTime},1)")
    boolean addCourse(Course course);

    @Update("update course set is_exist=0 where c_id=#{cId}")
    boolean deleteCourse(Integer cId);

    @Update("update course set name=#{name} where c_id=#{cId}")
    boolean updateName(Course course);

}
