package com.zz.Service;

import com.zz.bean.Exam;
import com.zz.dao.ExamDao;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ExamService {

//    创建考试
public TempResult createExam(Exam exam);

//    注销考试
public   TempResult deleteExam(Integer examId);


public ApiResult selectAll(String pageNum);
public ApiResult selectOne(Integer examId);
}
