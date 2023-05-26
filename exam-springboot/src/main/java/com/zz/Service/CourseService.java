package com.zz.Service;

import com.zz.bean.Course;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;

import java.util.List;

public interface CourseService {

    ApiResult selectByUId(Integer uId);

    boolean addCourse(Course course);

    boolean deleteCourse(Integer cId);

    boolean updateName(Course course);

    TempResult joinCourse(Integer uId, String code);

    /**
     *
     * @param uId 教师的uId
     * @return Apiresult中data为ArrayList<JsonObject>
     */
    ApiResult selectStudentAndCourse(Integer uId);

}
