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
import java.util.Set;

@RestController
    @RequestMapping("/api/exam")
public class Examcontroller {
    static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private ExamService examService;

    @PostMapping("/createExam")
    public ApiResult createExam(@RequestBody Map<String, Object> map) {
//        读取map数据注入exam
        Exam exam = new Exam();
        exam.setcId(Integer.parseInt((String) map.get("cid")));
        exam.setpId(Integer.parseInt((String) map.get("pid")));
        exam.setName((String) map.get("name"));
        String endTime = (String) map.get("endTime");
//        System.out.println(endTime.getClass());
        String startTime = (String) map.get("startTime");
        //字符串格式化为时间 LocalDateTime类型
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
    public ApiResult deleteExam(@PathVariable String examId) {
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

    @PutMapping("/updateExam")
    public ApiResult updateExamInfo(@RequestBody Map<String, Object> map){
    //        封装Exam
        System.out.println(map.toString());
        Exam exam = new Exam();

        exam.seteId(Integer.parseInt((String) map.get("eid")));
        exam.setpId(Integer.parseInt((String) map.get("pid")));
        exam.setcId(Integer.parseInt((String) map.get("cid")));
        exam.setName((String) map.get("name"));
        String endTime = (String) map.get("endTime");
        String startTime = (String) map.get("startTime");
        //字符串格式化为时间 LocalDateTime类型
        exam.setEndTime(LocalDateTime.parse(endTime, DATE_TIME_FORMATTER));
        exam.setStartTime(LocalDateTime.parse(startTime, DATE_TIME_FORMATTER));
        //更新操作
        ApiResult apiResult = examService.updateExamInfo(exam);
        if (apiResult.getData() != null){
            apiResult.setCode(Code.UPDATE_OK);
        }else {
            apiResult.setCode(Code.UPDATE_ERR);
        }
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
