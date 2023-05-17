package com.zz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.Service.ExamService;
import com.zz.bean.Exam;
import com.zz.controller.Examcontroller;
import com.zz.dao.ExamDao;
import com.zz.utils.result.ApiResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamTest {
    @Autowired
    private ExamDao examDao;
    @Autowired
    private ExamService examService;
    @Autowired
    private Examcontroller examcontroller;
    @Test
    public void ExamCreate(){
         Exam exam = new Exam();
         exam.setcId(1);
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

    @Test
    public void ExamDelete(){
        String examId = "1";
        examDao.deleteExam(Integer.parseInt(examId));
    }

    @Test
    public void ExamPageHelper(){
        String pageNum = "1";
        Page<?> page = PageHelper.startPage(Integer.parseInt(pageNum), 5);  //设置第几条记录开始，多少条记录为一页
        //通过userService获取user的信息，其sql语句为"select * from user" 但因pagehelp已经注册为插件，所以pagehelp会在原sql语句上增加limit，从而实现分页
        List<Exam> exams = examDao.selectAll();  //因而获得的是分好页的结果集
        PageInfo<?> pageHelper = page.toPageInfo(); //获取页面信息的对象，里面封装了许多页面的信息 如：总条数，当前页码，需显示的导航页等等
        Map<List<Exam>,PageInfo> examMap = new HashMap<>();
        examMap.put(exams,pageHelper);
        System.out.println(examMap.toString());
    }

    @Test
    public  void ExamGet(){
         List<Exam> exams = examDao.selectAll();
        for (Exam e:
        exams) {
            System.out.println(e);
        }
    }

    @Test
    public  void ExamGetOne(){
        System.out.println( examDao.selectOne(1));;
    }


    @Test
    public  void ExamGetAll(){
         ApiResult apiResult = examcontroller.selectAll("1");
        System.out.println(apiResult.toString());

    }


    @Test
    public  void ExamUpdateInfo(){
        Exam exam = new Exam();
        exam.setpId(2);
        ApiResult apiResult = examService.updateExamInfo(exam);
        System.out.println(apiResult.toString());

    }
}
