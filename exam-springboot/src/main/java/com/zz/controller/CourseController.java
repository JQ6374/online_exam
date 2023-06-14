package com.zz.controller;

import com.alibaba.fastjson.JSONObject;
import com.zz.Service.CourseService;
import com.zz.bean.Course;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{uId}")
    public ApiResult<List<Course>> selectByUId(@PathVariable Integer uId) {
        return courseService.selectByUId(uId);
    }

    /**
     * 修改所有的课程密码
     *
     * @param param
     */
    @PutMapping("/updateStudentByCourse")
    public ApiResult<Object> updateStudentByCourse(@RequestBody JSONObject param) {
        return courseService.updateStudentByCourse(
                param.getInteger("cId"),
                param.getInteger("ucId"));
    }

    @PostMapping("/add")
    public ApiResult<Object> addCourse(@RequestBody Course course) {
        boolean flag = courseService.addCourse(course);
        return new ApiResult<>(flag ? Code.SAVA_OK : Code.SAVA_ERR, null,
                flag ? "课程创建成功！" : "课程创建失败！");
    }

    /**
     * 教师修改课程名称
     *
     * @param course 课程Id
     */
    @PutMapping("/updateName")
    public ApiResult<Object> updateName(@RequestBody Course course) {
        boolean flag = courseService.updateName(course);
        return new ApiResult<>(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, null,
                flag ? "课程名称修改成功！" : "课程名称修改失败！");
    }

    /**
     * 教师删除课程
     *
     * @param cId 教师Id
     */
    @DeleteMapping("/{cId}")
    public ApiResult<Object> deleteCourse(@PathVariable Integer cId) {
        boolean flag = courseService.deleteCourse(cId);
        return new ApiResult<>(flag ? Code.DELETE_OK : Code.DELETE_ERR, null,
                flag ? "课程删除成功！" : "课程删除失败！");
    }

    @PostMapping("/joinCourse")
    public ApiResult<Object> joinCourse(@RequestBody Map<String, String> params) {
        System.out.println(params);
        TempResult tempResult = courseService.joinCourse(Integer.valueOf(params.get("uId")), params.get("courseCode"));
        return new ApiResult<>(tempResult.isFlag() ? Code.SAVA_OK : Code.SAVA_ERR,
                null, tempResult.getMsg());
    }

    /**
     * 教师根据课程名进行搜索
     *
     * @param uId
     * @param courseName
     * @return
     */
    @GetMapping("/{uId}/{courseName}")
    public ApiResult<List<Course>> searchCourseByName(@PathVariable Integer uId,
                                        @PathVariable String courseName) {
        return courseService.searchCourseByName(uId, courseName);
    }

    /**
     * 学生查询已经加入的课程
     * @param uId 学生Id
     */
    @GetMapping("/student/{uId}")
    public ApiResult<ArrayList<JSONObject>> selectChoiceCourseByUId(@PathVariable Integer uId) {
        return courseService.selectChoiceCourseByUId(uId);
    }

}
