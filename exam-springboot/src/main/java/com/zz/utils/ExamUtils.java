package com.zz.utils;

import java.time.LocalDateTime;

public class ExamUtils {
    public static Integer getExamStatus(LocalDateTime start_time, LocalDateTime end_time) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(start_time)) {
            return 0;
        } else if (now.isAfter(end_time)) {
            return 0;
        } else {
            return 1;
        }
    }
}
