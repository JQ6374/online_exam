package com.zz.utils;

import java.util.Random;

public class CourseUtils {
    public static String getCourseCode() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        code.append(System.currentTimeMillis() % 10000);
        for (int i = 0; i < 4; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
