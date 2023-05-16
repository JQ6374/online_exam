package com.zz.controller;

import com.zz.Service.ExamService;

import com.zz.bean.Exam;
import com.zz.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam")
public class Examcontroller {
    @Autowired
    private ExamService examService;
    @PostMapping("/createExam")
    public void createExam(@RequestBody Exam exam){
        examService.createExam(exam);
    }
}
