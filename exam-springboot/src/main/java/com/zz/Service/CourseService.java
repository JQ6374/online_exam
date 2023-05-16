package com.zz.Service;

import com.zz.bean.Course;

public interface CourseService {

    boolean addCourse(Course course);

    boolean deleteCourse(Integer cId);

    boolean updateName(Course course);

}
