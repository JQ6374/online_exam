package com.zz.Service.impl;

import com.zz.Service.ExamService;
import com.zz.bean.Exam;
import com.zz.dao.ExamDao;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDao examDao;
    @Override
    public TempResult createExam(Exam exam) {
        TempResult tempResult = new TempResult();
        Integer exam1 = examDao.createExam(exam);
        if(exam1 !=0){
            tempResult.setFlag(true);
            tempResult.setMsg("创建成功！");
        }else {
            tempResult.setFlag(false);
            tempResult.setMsg("创建失败！");
        }
        return tempResult;
    }

    @Override
    public TempResult deleteExam(Integer examId) {
        TempResult tempResult = new TempResult();
        Integer exam1 = examDao.deleteExam(examId);
        if(exam1 !=0){
            tempResult.setFlag(true);
            tempResult.setMsg("删除成功！");
        }else {
            tempResult.setFlag(false);
            tempResult.setMsg("删除失败！");
        }
        return tempResult;
    }
}
