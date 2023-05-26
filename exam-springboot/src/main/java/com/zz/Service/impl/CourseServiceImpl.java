package com.zz.Service.impl;

import com.zz.Service.CourseService;
import com.zz.bean.Course;
import com.zz.dao.CourseDao;
import com.zz.utils.Code;
import com.zz.utils.CourseUtils;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public ApiResult selectByUId(Integer uId) {
        ArrayList<Course> courses = courseDao.selectByUId(uId);
        System.out.println(courses);
        boolean isEmpty = courses.isEmpty();
        return new ApiResult(Code.GET_OK, courses, !isEmpty ? "查询成功！" : "查询结果为空！");
    }

    @Override
    public boolean addCourse(Course course) {
        course.setCourseCode(CourseUtils.getCourseCode());
        course.setCreateTime(LocalDateTime.now());
        return courseDao.addCourse(course);
    }

    @Override
    public boolean deleteCourse(Integer cId) {
        return courseDao.deleteCourse(cId);
    }

    @Override
    public boolean updateName(Course course) {
        return courseDao.updateName(course);
    }

    @Override
    public TempResult joinCourse(Integer uId, String code) {
        Integer cId = courseDao.selectByCode(code);
        TempResult tempResult = new TempResult();
        if (cId != null) {
            Integer count = courseDao.isJoin(uId, cId);
            if (count == 0) {
                tempResult.setFlag(courseDao.joinCourse(uId, cId));
                tempResult.setMsg(tempResult.isFlag() ? "课程加入成功！" : Code.ERROR_MSG);
            } else {
                tempResult.setFlag(false);
                tempResult.setMsg("已加入课程，请勿重复加入！");
            }
        } else {
            tempResult.setFlag(false);
            tempResult.setMsg("课程不存在，请检查课程码是否正确！");
        }
        return tempResult;
    }


}
