package com.zz.Service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.Service.ExamService;
import com.zz.bean.Exam;
import com.zz.dao.ExamDao;
import com.zz.utils.Code;
import com.zz.utils.SplitObjectAndSubject;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import com.zz.utils.subjecttiveJudge.ScorePointSim;
import com.zz.utils.subjecttiveJudge.SentenceSeparation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamDao examDao;

    //创建考试
    @Override
    public TempResult createExam(Exam exam) {
        System.out.println("create Exam print" + exam.toString());
        TempResult tempResult = new TempResult();
        Integer exam1 = examDao.createExam(exam);
        if (exam1 != 0) {
            tempResult.setFlag(true);
            tempResult.setMsg("创建成功！");
        } else {
            tempResult.setFlag(false);
            tempResult.setMsg("创建失败！");
        }
        return tempResult;
    }

    //注销考试
    @Override
    public TempResult deleteExam(Integer examId) {
        TempResult tempResult = new TempResult();
        Integer exam1 = examDao.deleteExam(examId);
        if (exam1 != 0) {
            tempResult.setFlag(true);
            tempResult.setMsg("删除成功！");
        } else {
            tempResult.setFlag(false);
            tempResult.setMsg("删除失败！");
        }
        return tempResult;
    }


    //考试信息更改 时间、试卷等。
    @Override
    public ApiResult updateExamInfo(Exam exam) {
        System.out.println(exam);
        Integer flag = examDao.updateExamInfo(exam);//返回更新后的考试实体
        ApiResult apiResult = new ApiResult();

        if (flag != null) {
            apiResult.setMsg("修改成功");
            apiResult.setData(exam);
        } else {
            apiResult.setMsg("修改失败");
            apiResult.setData(null);//返回原exam
        }
        return apiResult;
    }

    @Override
    public ApiResult selectAll(Integer uId) {
        ApiResult apiResult = new ApiResult();
//        Page<?> page = PageHelper.startPage(Integer.parseInt(pageNumNow), 5);  //设置第几条记录开始，多少条记录为一页
        //通过userService获取user的信息，其sql语句为"select * from user" 但因pageHelper已经注册为插件，所以pageHelper会在原sql语句上增加limit，从而实现分页
        List<Exam> exams = examDao.selectAll(uId);//因而获得的是分好页的结果集
//        PageInfo<?> pageHelper = page.toPageInfo(); //获取页面信息的对象，里面封装了许多页面的信息 如：总条数，当前页码，需显示的导航页等等
        apiResult.setData(exams);
        if (apiResult.getData() != null) {
            apiResult.setMsg("数据查询成功");
            apiResult.setCode(Code.GET_OK);
        } else {
            apiResult.setMsg("数据查询失败!");
            apiResult.setCode(Code.GET_ERR);
        }
        return apiResult;
    }

    @Override
    public ApiResult selectOne(Integer examId) {
        /**
         * 查询结果
         */
        Exam exam = examDao.selectOne(examId);
        ApiResult apiResult = new ApiResult();
        if (exam != null) {
            apiResult.setData(exam);
            apiResult.setCode(Code.GET_OK);
            apiResult.setMsg("查询成功！");
        } else {
            apiResult.setData(null);
            apiResult.setCode(Code.GET_ERR);
            apiResult.setMsg("查询失败！");
        }
        return apiResult;
    }

    /**
     * 对待评分答试卷和参考答案试卷进行题型提取，使用判分util进行评分
     *
     * @param jsonObject 前端传来的json数据
     * @return apiresult中是一个含有分数、正确答案的新的paper
     */
    @Override
    public ApiResult judge(JSONObject jsonObject) {
        JSONObject returnJson = jsonObject;
//        待评试卷答案处理操作
        List<JSONObject> jsonObjects = SplitObjectAndSubject.splitMethod(jsonObject.getJSONObject("content"));
        //        客观题带评答案获取
        JSONObject objectiveJson = jsonObjects.get(0);
        //        主观题带评答案获取
        JSONObject subjectiveJson = jsonObjects.get(1);
        JSONArray subjArray = subjectiveJson.getJSONArray("5");
        //        获取试卷参考答案
        Integer eId = (Integer) jsonObject.get("eId");
        Exam exam = examDao.selectOne(eId);
        String content = exam.getContent();
        //        将字符型的转换为json格式
        JSONObject checkAnswer = JSONObject.parseObject(content);
        List<JSONObject> checkJsons = SplitObjectAndSubject.splitMethod(checkAnswer);
        //        客观题答案获取
        JSONObject objectiveCheckJson = checkJsons.get(0);
        //        主观题答案获取
        JSONObject subjectiveCheckJson = checkJsons.get(1);



        //        客观题评分处理
        JSONArray subjCheckArray = subjectiveCheckJson.getJSONArray("5");
        /**  主观题评分处理 提取所有jsonArray中的answer 既为主观题答案序列，
         * 一次将一题的答案给util判分，并将带评答案和分数填入返回
         * 以下为answer的格式
         * {"score":5,
         * "answer":"{\"answer\":\"呼呼，我是第一题的答案\",\"typeId\":5}",
         * "question":"这是主观题","tId":2}
         * 发现题号是对应的，一题一题对比即可
         */
        //subjArray subjCheckArray\
        List<String> subjArraySenten = null;
        List<String> subjCheckArraySenten = null;
        String jsonObject1 = null;
        String jsonObject2 = null;

        for (int i = 0; i < subjArray.size(); i++) {

            jsonObject1 = (String) ((JSONObject) ((JSONObject) subjArray.get(i)).get("answer")).get("answer");
            jsonObject2 = (String) ((JSONObject) ((JSONObject) subjCheckArray.get(i)).get("answer")).get("answer");
//            subjArraySenten.add(SentenceSeparation.sentenceList(jsonObject1));//待评分句
//            subjCheckArraySenten.add(SentenceSeparation.sentenceList(jsonObject2));//分句

            subjArraySenten = SentenceSeparation.sentenceList(jsonObject1);//待评分句
            subjCheckArraySenten = SentenceSeparation.sentenceList(jsonObject2);//分句
            double score = ScorePointSim.getScorePointSim(subjCheckArraySenten, subjArraySenten);
            System.out.println(score);
            //拿到分数后填入jsonObject中，返回ApiResult
//           JSONObject o = (JSONObject) subjArray.get(i);
//           o.put("rightAnswer",JSONObject.parseObject(jsonObject1));
//           o.put("score",score);
        }

        return null;
    }
}
