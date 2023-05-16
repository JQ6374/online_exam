package com.zz.Service;

import com.zz.bean.Course;
import com.zz.utils.result.TempResult;

public interface CourseService {

    boolean addCourse(Course course);

    boolean deleteCourse(Integer cId);

    boolean updateName(Course course);

    TempResult joinCourse(Integer uId, String code);

}
