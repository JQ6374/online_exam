package com.zz.Service;

import com.zz.bean.Exam;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;


public interface ExamService {

//    创建考试
public TempResult createExam(Exam exam);

//    注销考试
public   TempResult deleteExam(Integer examId);


public ApiResult selectAll(String pageNum);
public TempResult selectOne(Integer examId);
}
