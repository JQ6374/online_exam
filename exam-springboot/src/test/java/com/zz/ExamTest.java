package com.zz;

import com.zz.bean.Exam;
import com.zz.dao.ExamDao;
import com.zz.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamTest {
    @Autowired
    private ExamDao examDao;
    @Test
    public void ExamCreate(){
         Exam exam = new Exam();
         exam.setCId(1);
//         //时间格式转换
         DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         String startTime = "2023-05-16 09:30:00";
         String endTime = "2023-05-16 11:30:00";
         exam.setEndTime(LocalDateTime.parse(endTime,DATE_TIME_FORMATTER));
         exam.setStartTime(LocalDateTime.parse(startTime,DATE_TIME_FORMATTER));
        System.out.println(exam);
//        System.out.println("成功");
//        examDao.createExam(exam);
    }
}
