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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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

    @GetMapping("/selectAll/{pageNum}")
    public ApiResult selectAll(@PathVariable("pageNum") String pageNumNow) {
        ApiResult apiResult  = examService.selectAll(pageNumNow);
        if(apiResult.getData() !=null){
            apiResult.setMsg("分页查询成功");
            apiResult.setCode(Code.GET_OK);
        }else {
            apiResult.setMsg("分页查询失败!");
            apiResult.setCode(Code.GET_ERR);
        }
        return apiResult;
    }

    @GetMapping("/selectOne/{examId}")
    public ApiResult selectOne(@PathVariable("examId") String examId) {
        ApiResult apiResult = examService.selectOne(Integer.parseInt(examId));
        System.out.println(apiResult.getData().toString());
        return apiResult;
    }
}
