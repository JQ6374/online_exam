package com.zz.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.Service.ExamService;
import com.zz.bean.Exam;
import com.zz.dao.ExamDao;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public ApiResult selectAll(String pageNum) {
        ApiResult apiResult = new ApiResult();
        Page<?> page = PageHelper.startPage(Integer.parseInt(pageNum), 5);  //设置第几条记录开始，多少条记录为一页

        //通过userService获取user的信息，其sql语句为"select * from user" 但因pagehelp已经注册为插件，所以pagehelp会在原sql语句上增加limit，从而实现分页
        List<Exam> exams = examDao.selectAll();  //因而获得的是分好页的结果集
        PageInfo<?> pageHelper = page.toPageInfo(); //获取页面信息的对象，里面封装了许多页面的信息 如：总条数，当前页码，需显示的导航页等等
        Map<List<Exam>,PageInfo> examMap = null;
        examMap.put(exams,pageHelper);
        apiResult.setData(examMap);
        return apiResult;
    }

    @Override
    public ApiResult selectOne(Integer examId) {
        ApiResult apiResult = new ApiResult();
        /**
         * 查询结果
         */
        return apiResult;
    }
}
