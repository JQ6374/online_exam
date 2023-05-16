package com.zz.Service.impl;

import com.zz.Service.CourseService;
import com.zz.bean.Course;
import com.zz.dao.CourseDao;
import com.zz.utils.CourseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

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
}
