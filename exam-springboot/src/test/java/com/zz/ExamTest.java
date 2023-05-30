package com.zz;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.Service.ExamService;
import com.zz.bean.Course;
import com.zz.bean.Exam;
import com.zz.controller.ExamController;
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
    private ExamController examcontroller;

    @Test
    public void ExamCreate() {
        Exam exam = new Exam();
        exam.setcId(1);
//         //时间格式转换
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startTime = "2023-05-16 09:30:00";
        String endTime = "2023-05-16 11:30:00";
        exam.setEndTime(LocalDateTime.parse(endTime, DATE_TIME_FORMATTER));
        exam.setStartTime(LocalDateTime.parse(startTime, DATE_TIME_FORMATTER));
        System.out.println(exam);
//        System.out.println("成功");
//        examDao.createExam(exam);
    }

    @Test
    public void ExamDelete() {
        String examId = "1";
        examDao.deleteExam(Integer.parseInt(examId));
    }

    @Test
    public void ExamPageHelper() {
        String pageNum = "1";
        Page<?> page = PageHelper.startPage(Integer.parseInt(pageNum), 5);  //设置第几条记录开始，多少条记录为一页
        //通过userService获取user的信息，其sql语句为"select * from user" 但因pagehelp已经注册为插件，所以pagehelp会在原sql语句上增加limit，从而实现分页
        List<Exam> exams = examDao.selectAll(9);  //因而获得的是分好页的结果集
        PageInfo<?> pageHelper = page.toPageInfo(); //获取页面信息的对象，里面封装了许多页面的信息 如：总条数，当前页码，需显示的导航页等等
        Map<List<Exam>, PageInfo> examMap = new HashMap<>();
        examMap.put(exams, pageHelper);
        System.out.println(examMap.toString());
    }

    @Test
    public void ExamGet() {
        List<Exam> exams = examDao.selectAll(9);
        for (Exam e :
                exams) {
            System.out.println(e);
        }
    }

    @Test
    public void ExamGetOne() {
        System.out.println(examDao.selectOne(1));
        ;
    }


    @Test
    public void ExamGetAll() {
        ApiResult apiResult = examcontroller.selectAll(9);
        System.out.println(apiResult.toString());

    }


    @Test
    public void ExamUpdateInfo() {
        Exam exam = new Exam();
        exam.setpId(2);
        ApiResult apiResult = examService.updateExamInfo(exam);
        System.out.println(apiResult.toString());

    }

    @Test
    public void getScoreFn() {
        JSONObject res = JSONObject.parseObject("{\n" +
                "  \"eId\":\"1\",\n" +
                "  \"content\": \"{\\\"1\\\":[{\\\"answer\\\":\\\"{\\\\\\\"answerContent\\\\\\\":\\\\\\\"1\\\\\\\",\\\\\\\"typeId\\\\\\\":1}\\\",\\\"question\\\":\\\"我被修改了\\\",\\\"score\\\":1,\\\"tId\\\":1},{\\\"answer\\\":\\\"{\\\\\\\"answerContent\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"typeId\\\\\\\":1}\\\",\\\"question\\\":\\\"这是一个判断题，难度为中等\\\",\\\"score\\\":1,\\\"tId\\\":4},{\\\"answer\\\":\\\"{\\\\\\\"answerContent\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"typeId\\\\\\\":1}\\\",\\\"question\\\":\\\"我被修改了，我是凑数的\\\",\\\"score\\\":1,\\\"tId\\\":5}],\\\"2\\\":[],\\\"3\\\":[{\\\"answer\\\":\\\"{\\\\\\\"answerContent\\\\\\\":\\\\\\\"[\\\\\\\\\\\\\\\"A\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"B\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"D\\\\\\\\\\\\\\\"]\\\\\\\",\\\\\\\"typeId\\\\\\\":3}\\\",\\\"question\\\":\\\"这是一个共享的题目\\\",\\\"score\\\":2,\\\"tId\\\":3},{\\\"answer\\\":\\\"{\\\\\\\"answerContent\\\\\\\":\\\\\\\"[\\\\\\\\\\\\\\\"A\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"C\\\\\\\\\\\\\\\"]\\\\\\\",\\\\\\\"typeId\\\\\\\":3}\\\",\\\"question\\\":\\\"这是一个多选题\\\",\\\"score\\\":2,\\\"tId\\\":6}],\\\"4\\\":[],\\\"5\\\":[{\\\"answer\\\":\\\"{\\\\\\\"answerContent\\\\\\\":\\\\\\\"呼呼，我是第一题的答案\\\\\\\",\\\\\\\"typeId\\\\\\\":5}\\\",\\\"question\\\":\\\"这是主观题\\\",\\\"score\\\":5,\\\"tId\\\":2}]}\"\n" +
                "}");
        examService.judge(res);
    }


    @Test
    public void getNameByID() {
        Exam exam = examDao.selectOne(1);
        System.out.println(exam.getTeacherName());
    }

    @Test
    public void getCoursebystu() {
        List<Course> coursesByUid = examDao.getCoursesByUid(16);
        System.out.println(coursesByUid.toString());
        for (Course course:
             coursesByUid) {
//            System.out.println(examDao.getExamsByCourseId(course.getcId()));
        }
    }
}
