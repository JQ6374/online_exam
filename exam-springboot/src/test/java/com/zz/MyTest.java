package com.zz;

import com.zz.utils.CourseUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@SpringBootTest
public class MyTest {

    @Test
    void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println(CourseUtils.getCourseCode());
        }
    }
}
