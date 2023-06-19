package com.zz.service;

import com.alibaba.fastjson.JSONObject;
import com.zz.bean.Exam;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;

import java.util.List;


public interface ExamService {

    // 创建考试
    TempResult createExam(Exam exam);

    // 注销考试
    TempResult deleteExam(Integer examId);

    // 信息更新 时间、试卷。
    ApiResult<Exam> updateExamInfo(Exam exam);

    // 查询
    ApiResult<List<Exam>> selectAll(Integer uId);

    ApiResult<Exam> selectOne(Integer examId);

    ApiResult<?> judge(JSONObject jsonObject);

    ApiResult<List<JSONObject>> getExamListByStu(Integer uId);

    ApiResult<Object> getExams();

    ApiResult<Boolean> isSubmit(Integer uId, Integer eId);

    ApiResult<List<JSONObject>> submitList(Integer uId);
}
