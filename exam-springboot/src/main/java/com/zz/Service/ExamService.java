package com.zz.Service;

import com.zz.bean.Exam;
import com.zz.dao.ExamDao;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface ExamService {
    TempResult createExam(Exam exam);
}
