package com.zz.Service;

import com.alibaba.fastjson.JSONObject;
import com.zz.bean.Course;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;

import java.util.ArrayList;

public interface CourseService {

    ApiResult selectByUId(Integer uId);

    boolean addCourse(Course course);

    boolean deleteCourse(Integer cId);

    boolean updateName(Course course);

    TempResult joinCourse(Integer uId, String code);

    /**
     *
     * @param uId 教师的uId
     * @return ApiResult接口
     */
    ApiResult selectStudentAndCourse(Integer uId);

    /**
     * 根据学生Id或者学生姓名搜索
     * @param uId 教师Id
     * @param studentIdOrName 学生Id或者姓名
     * @return ApiResult接口
     */
    ApiResult searchStudentAndCourse(Integer uId, String studentIdOrName);

    /**
     * 根据课程名进行搜索
     * @param uId 教师Id
     * @param courseName 课程名
     * @return ApiResult接口
     */
    ApiResult searchCourseByName(Integer uId, String courseName);

    /**
     * 修改学生所选的课程
     * @param cId 课程Id
     * @param ucId 选课Id
     * @return ApiResult接口
     */
    ApiResult updateStudentByCourse(Integer cId, Integer ucId);

    /**
     * 将学生移除课程
     * @param ucId 选课Id
     * @return ApiResult接口
     */
    ApiResult deleteStudentByCourse(Integer ucId);

    ApiResult<ArrayList<JSONObject>> selectChoiceCourseByUId(Integer uId);
}
