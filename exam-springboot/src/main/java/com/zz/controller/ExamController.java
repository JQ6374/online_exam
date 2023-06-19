package com.zz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zz.service.ExamService;

import com.zz.bean.Exam;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
    static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private ExamService examService;

    @PostMapping("/judge")
    public ApiResult<?> judgeScore(@RequestBody JSONObject jsonObject) {
        return examService.judge(jsonObject);
    }

    @PostMapping("/createExam")
    public ApiResult<Object> createExam(@RequestBody JSONObject param) {
//        读取map数据注入exam
        Exam exam = new Exam();
        exam.setcId(param.getInteger("cId"));
        exam.setpId(param.getInteger("pId"));
        exam.setuId(param.getInteger("uId"));
        exam.setName(param.getString("name"));

        //字符串格式化为时间 LocalDateTime类型
        exam.setStartTime(param.getObject("startTime", LocalDateTime.class));
        exam.setEndTime(param.getObject("endTime", LocalDateTime.class));
        TempResult tempResult = examService.createExam(exam);
        ApiResult<Object> apiResult = new ApiResult<>();
        if (tempResult.isFlag()) {
            apiResult.setCode(Code.SAVA_OK);
        } else {
            apiResult.setCode(Code.SAVA_ERR);
        }
        apiResult.setMsg(tempResult.getMsg());
        return apiResult;
    }

    @DeleteMapping("/{examId}")
    public ApiResult<Object> deleteExam(@PathVariable String examId) {
        Integer id = Integer.parseInt(examId);
        TempResult tempResult = examService.deleteExam(id);
        ApiResult<Object> apiResult = new ApiResult<>();
        if (tempResult.isFlag()) {
            apiResult.setCode(Code.DELETE_OK);
        } else {
            apiResult.setCode(Code.DELETE_ERR);
        }
        apiResult.setMsg(tempResult.getMsg());
        return apiResult;
    }

    @PutMapping("/updateExam")
    public ApiResult<Exam> updateExamInfo(@RequestBody JSONObject param) {
        // 封装Exam
        Exam exam = new Exam();
        exam.seteId(param.getInteger("eId"));
        exam.setuId(param.getInteger("uId"));
        exam.setpId(param.getInteger("pId"));
        exam.setcId(param.getInteger("cId"));
        exam.setName(param.getString("name"));
        exam.setStatus(param.getInteger("status"));
        exam.setIsExist(param.getInteger("isExist"));

        //字符串格式化为时间 LocalDateTime类型
        exam.setEndTime(param.getObject("endTime", LocalDateTime.class));
        exam.setStartTime(param.getObject("startTime", LocalDateTime.class));
        //更新操作
        ApiResult<Exam> apiResult = examService.updateExamInfo(exam);
        if (apiResult.getData() != null) {
            apiResult.setCode(Code.UPDATE_OK);
        } else {
            apiResult.setCode(Code.UPDATE_ERR);
        }
        return apiResult;
    }

    @GetMapping("/{uId}")
    public ApiResult<List<Exam>> selectAll(@PathVariable("uId") Integer uId) {
        return examService.selectAll(uId);
    }

    /**
     * 老师查询的能看到答案
     *
     * @param examId 考试Id
     */
    @GetMapping("/selectOne/{examId}")
    public ApiResult<Exam> selectOne(@PathVariable("examId") Integer examId) {
        return examService.selectOne(examId);
    }

    /**
     * 学生查询的，看不到答案
     *
     * @param examId 考试Id
     */
    @GetMapping("/selectOne/student/{examId}")
    public ApiResult<Exam> selectOneStu(@PathVariable("examId") String examId) {
        ApiResult<Exam> apiResult = examService.selectOne(Integer.parseInt(examId));
        JSONObject content = JSONObject.parseObject(apiResult.getData().getContent());
        for (String key : content.keySet()) {
            JSONArray jsonArray = content.getJSONArray(key);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONObject answer = obj.getJSONObject("answer");
                if (answer.getInteger("typeId") == 3) {
                    answer.put("answerContent", new ArrayList<>());
                } else {
                    answer.put("answerContent", "");
                }
                content.getJSONArray(key).getJSONObject(i).put("answer", answer);
            }
        }
        apiResult.getData().setContent(content.toString());
        return apiResult;
    }

    @GetMapping("/getStuExams/{uId}")
    public ApiResult<List<JSONObject>> getExamListByStu(@PathVariable("uId") Integer uId) {
        return examService.getExamListByStu(uId);
    }

    @GetMapping("/updateAllStatus")
    public ApiResult<Object> getExamListByStu() {
        return examService.getExams();
    }

    @GetMapping("/{uId}/{eId}")
    public ApiResult<Boolean> isSubmit(@PathVariable("uId") Integer uId,
                                       @PathVariable("eId") Integer eId) {
        return examService.isSubmit(uId, eId);
    }

    @GetMapping("/submitList/{uId}")
    public ApiResult<List<JSONObject>> submitList(@PathVariable("uId") Integer uId) {
        return examService.submitList(uId);
    }
}
