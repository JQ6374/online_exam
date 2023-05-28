package com.zz.Service;

import com.zz.bean.Exam;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;


public interface ExamService {

    //    创建考试
    public TempResult createExam(Exam exam);

    //    注销考试
    public TempResult deleteExam(Integer examId);

    //信息更新 时间、试卷。
    public ApiResult updateExamInfo(Exam exam);

    //查询
    public ApiResult selectAll(Integer uId);

    public ApiResult selectOne(Integer examId);
}
