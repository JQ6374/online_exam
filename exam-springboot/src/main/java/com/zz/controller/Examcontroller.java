package com.zz.controller;

import com.zz.Service.ExamService;

import com.zz.bean.Exam;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam")
public class Examcontroller {
    @Autowired
    private ExamService examService;
    @PostMapping("/createExam")
    public ApiResult createExam(@RequestBody Exam exam){
         TempResult tempResult = examService.createExam(exam);
        ApiResult apiResult = new ApiResult();
        if (tempResult.isFlag()){
            apiResult.setCode(Code.SAVA_ERR);
        }else {
            apiResult.setCode(Code.SAVA_ERR);
        }
        apiResult.setMsg(tempResult.getMsg());
         return apiResult;
    }
}
