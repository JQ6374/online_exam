package com.zz.Service.impl;

import com.zz.Service.ExamService;
import com.zz.bean.Exam;
import com.zz.dao.ExamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDao examDao;
    @Override
    public Integer createExam(Exam exam) {
        return  examDao.createExam(exam);
    }
}
