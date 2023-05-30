package com.zz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zz.Service.ExamService;

import com.zz.bean.Exam;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
    static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private ExamService examService;

    @PostMapping("/judge")
    public ApiResult judgeScore(@RequestBody JSONObject jsonObject) {
        return examService.judge(jsonObject);
    }

    @PostMapping("/createExam")
    public ApiResult createExam(@RequestBody Map<String, Object> map) {
//        读取map数据注入exam
        Exam exam = new Exam();
        exam.setcId(Integer.parseInt((String) map.get("cId")));
        exam.setpId(Integer.parseInt((String) map.get("pId")));
        exam.setName((String) map.get("name"));
        String endTime = (String) map.get("endTime");
//        System.out.println(endTime.getClass());
        String startTime = (String) map.get("startTime");
        exam.setuId(Integer.parseInt((String) map.get("uId")));
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
    public ApiResult updateExamInfo(@RequestBody Map<String, Object> map) {
        //        封装Exam
        System.out.println(map.toString());
        Exam exam = new Exam();
        exam.seteId(Integer.parseInt((String) map.get("eid")));
        exam.setuId(Integer.parseInt((String) map.get("uid")));
        exam.setpId(Integer.parseInt((String) map.get("pid")));
        exam.setcId(Integer.parseInt((String) map.get("cid")));
        exam.setName((String) map.get("name"));
        exam.setStatus(Integer.parseInt((String) map.get("status")));
        exam.setIsExist(Integer.parseInt((String) map.get("isExist")));
        String endTime = (String) map.get("endTime");
        String startTime = (String) map.get("startTime");
        //字符串格式化为时间 LocalDateTime类型
        exam.setEndTime(LocalDateTime.parse(endTime, DATE_TIME_FORMATTER));
        exam.setStartTime(LocalDateTime.parse(startTime, DATE_TIME_FORMATTER));
        //更新操作
        ApiResult apiResult = examService.updateExamInfo(exam);
        if (apiResult.getData() != null) {
            apiResult.setCode(Code.UPDATE_OK);
        } else {
            apiResult.setCode(Code.UPDATE_ERR);
        }
        return apiResult;
    }

    @GetMapping("/{uId}")
    public ApiResult selectAll(@PathVariable("uId") Integer uId) {
        return examService.selectAll(uId);
    }

    /**
     * 老师查询的能看到答案
     *
     * @param examId
     * @return
     */
    @GetMapping("/selectOne/{examId}")
    public ApiResult selectOne(@PathVariable("examId") String examId) {
        ApiResult apiResult = examService.selectOne(Integer.parseInt(examId));
        return apiResult;
    }

    /**
     * 学生查询的，看不到答案
     *
     * @param examId
     * @return
     */
    @GetMapping("/selectOne/student/{examId}")
    public ApiResult selectOneStu(@PathVariable("examId") String examId) {
        ApiResult<Exam> apiResult = examService.selectOne(Integer.parseInt(examId));
        JSONObject content = JSONObject.parseObject(apiResult.getData().getContent());
        for (String key : content.keySet()) {
            JSONArray jsonArray = content.getJSONArray(key);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                JSONObject answer = obj.getJSONObject("answer");
                answer.put("answerContent", "");
                content.getJSONArray(key).getJSONObject(i).put("answer", answer);
            }
        }
        apiResult.getData().setContent(content.toString());
        return apiResult;
    }

    @GetMapping("/getstuExams/{uId}")
    public ApiResult getExamListBystu(@PathVariable("uId") Integer uId) {
        System.out.println("=========================" + uId);
        return examService.getExamListBystu(uId);
    }
}
