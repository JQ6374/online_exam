package com.zz.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.Service.ExamService;

import com.zz.bean.Exam;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exam")
public class Examcontroller {
    @Autowired
    private ExamService examService;

    @PostMapping("/createExam")
    public ApiResult createExam(@RequestBody Map<String, Object> map) {
//        读取map数据注入exam
        Exam exam = new Exam();
        exam.setcId(Integer.parseInt((String) map.get("cid")));
        exam.setName((String) map.get("name"));
        String endTime = (String) map.get("end_time");
        String startTime = (String) map.get("start_time");
        //字符串格式化为时间 LocalDateTime类型
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        exam.setEndTime(LocalDateTime.parse(endTime, DATE_TIME_FORMATTER));
        exam.setStartTime(LocalDateTime.parse(startTime, DATE_TIME_FORMATTER));
        TempResult tempResult = examService.createExam(exam);
//        System.out.println(map);
        ApiResult apiResult = new ApiResult();
        if (tempResult.isFlag()) {
            apiResult.setCode(Code.SAVA_ERR);
        } else {
            apiResult.setCode(Code.SAVA_ERR);
        }
        apiResult.setMsg(tempResult.getMsg());
        return apiResult;
    }

    @DeleteMapping("/{examId}")
    public ApiResult createExam(@PathVariable String examId) {
        Integer id = Integer.parseInt(examId);
        System.out.println(id);
        TempResult tempResult = examService.deleteExam(id);
        ApiResult apiResult = new ApiResult();
        if (tempResult.isFlag()) {
            apiResult.setCode(Code.DELETE_OK);
        } else {
            apiResult.setCode(Code.DELETE_ERR);
        }
        apiResult.setMsg(tempResult.getMsg());
        return apiResult;
    }

    @GetMapping("/{pageNum}")
    public ApiResult selectAll(@PathVariable("pageNum") String pageNum) {
        ApiResult apiResult = new ApiResult();

        return apiResult;
    }

}
