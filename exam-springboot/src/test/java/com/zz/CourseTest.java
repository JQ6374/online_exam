package com.zz;

import com.zz.bean.Course;
import com.zz.dao.CourseDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseTest {

    @Autowired
    private CourseDao courseDao;

    @Test
    void joinTest() {
        System.out.println(courseDao.isJoin(2, 8));
    }
}
