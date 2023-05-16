package com.zz.Service;

import com.zz.bean.Exam;
import com.zz.dao.ExamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface ExamService {
    Integer createExam(Exam exam);
}
