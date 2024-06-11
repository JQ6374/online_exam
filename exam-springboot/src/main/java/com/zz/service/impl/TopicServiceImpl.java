package com.zz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zz.dao.DifficultyDao;
import com.zz.service.TopicService;
import com.zz.bean.Papers;
import com.zz.bean.Topic;
import com.zz.bean.PapersContent;
import com.zz.dao.PapersDao;
import com.zz.dao.TopicDao;
import com.zz.dao.TopicTypeDao;
import com.zz.utils.AnswerUtils;
import com.zz.utils.Code;
import com.zz.utils.result.ApiResult;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PapersDao papersDao;

    @Autowired
    private TopicTypeDao topicTypeDao;

    @Autowired
    private DifficultyDao difficultyDao;

    /**
     * 添加题目
     */
    @Override
    public TempResult addTopic(Topic topic) {
        TempResult tempResult = new TempResult();
        topic.setCreateTime(LocalDateTime.now());
        topic.setAnswer(AnswerUtils.formatAnswer(topic.getTypeId(), topic.getAnswer()));
        Integer integer = topicDao.addTopic(topic);
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "题目添加成功！" : Code.ERROR_MSG);
        return tempResult;
    }

    /**
     * 查询该老师创建的所有题目
     *
     * @param uId 教师Id
     * @return 题目集合
     */
    @Override
    public ArrayList<Topic> selectByUId(Integer uId) {
        return topicDao.selectByUId(uId);
    }

    /**
     * 根据题型、标签、难度、题目进行搜索
     *
     * @param uId  教师Id
     * @param info 搜索值
     * @return 题目集合
     */
    @Override
    public ApiResult<List<Topic>> searchByInfo(Integer uId, String info) {
        ArrayList<Topic> topicList = topicDao.selectByUId(uId);
        List<Topic> res = topicList.stream()
                .filter(item -> {
                    boolean b1 = info.equals(item.getTypeName());
                    boolean b2 = info.equals(item.getTagName());
                    boolean b3 = info.equals(item.getDifficultyName());
                    boolean b4 = item.getQuestion().contains(info);
                    return b1 || b2 || b3 || b4;
                })
                .collect(Collectors.toList());
        int count = res.size();
        return new ApiResult<>(Code.GET_OK, res,
                count != 0 ? "查询出" + count + "道题目！" : "未找到改题目！");
    }

    /**
     * 删除题目，逻辑删除
     *
     * @param tId 题目Id
     */
    @Override
    public TempResult delTopic(Integer tId) {
        TempResult tempResult = new TempResult();
        Integer integer = topicDao.delTopic(tId);
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "删除成功！" : Code.ERROR_MSG);
        return tempResult;
    }

    /**
     * 修改题目
     *
     * @param topic 题目对象
     */
    @Override
    public TempResult updateTopic(Topic topic) {
        TempResult tempResult = new TempResult();
        topic.setAnswer(AnswerUtils.formatAnswer(topic.getTypeId(), topic.getAnswer()));
        Integer integer = topicDao.updateTopic(topic);
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "修改成功！" : Code.ERROR_MSG);
        return tempResult;
    }

    /**
     * 手动组卷
     *
     * @param tIds       题目Id
     * @param papersName 试卷名称
     * @param topicScore 分数字典
     */
    @Override
    public TempResult topicToPapers(Integer uId, Integer[] tIds, String papersName, JSONObject topicScore) {
        JSONObject result = new JSONObject();
        // 查到所有的试卷类型
        topicTypeDao.selectAll().forEach(item ->
                result.put(String.valueOf(item.getTypeId()), new ArrayList<>()));
        // 存放试卷的格式
        // {
        //"1": [xx,xx,xx],
        //"2":[],
        //"3":[xx,xx],
        //....
        //}

        TempResult tempResult = new TempResult();
        for (Topic topic : topicDao.topicToPapers(tIds)) {
            // topic.getTypeId()获取题目的类型
            result.getJSONArray(String.valueOf(topic.getTypeId()))   // "1": [],
                    .add(new PapersContent(
                            topic.gettId(),
                            topic.getQuestion(),
                            topic.getAnswer(),
                            topicScore.getInteger(String.valueOf(topic.getTypeId()))));
        }

        Integer integer = papersDao.addPaper(new Papers(uId, papersName, result.toString(), LocalDateTime.now()));
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "试卷生成成功！" : Code.ERROR_MSG);
        return tempResult;
    }

    /**
     * 随机组卷
     *
     * @param params example：
     *               {
     *               "uId": 9,               // 教师Id
     *               "tagId": 1,             // 标签（试卷类型）Id
     *               "papersName": "随机组卷",   // 考卷名字
     *               "topicScore": {         // 题型对应的分数
     *               "1": 1,
     *               "2": 2,
     *               "3": 2,
     *               "4": 2,
     *               "5": 5
     *               },
     *               "check": {              // 约束
     *               "typeCheck": {          // 题型数量约束
     *               "1": 3,  // typeId: topicNum
     *               "2": 0,
     *               "3": 0,
     *               "4": 0,
     *               "5": 1
     *               },
     *               "difficultyCheck": {     // 难度分布
     *               "1": 0.7,  // 百分比
     *               "2": 0.2,
     *               "3": 0.1
     *               }
     *               }
     *               }
     */
    @Override
    public TempResult topicToPapers(JSONObject params) {
        TempResult tempResult = new TempResult();
        Integer uId = params.getInteger("uId");
        Integer tagId = params.getInteger("tagId");
        String papersName = params.getString("papersName");
        JSONObject topicScore = params.getJSONObject("topicScore");
        JSONObject check = params.getJSONObject("check");
        // 题目类型数量约束
        JSONObject typeCheck = check.getJSONObject("typeNumCheck");
        // 难度系数分部约束
        JSONObject difficultyCheck = check.getJSONObject("difficultyCheck");
        ArrayList<JSONObject> typeCountList = topicDao.typeCount(tagId);
        JSONObject typeCount = new JSONObject();
        typeCountList.forEach((item) -> {
            typeCount.put(item.getString("typeId"), item.getInteger("count"));
        });

        /**
         * 判断题目数量是否充足
         */
        // 题目数量不够的类型 ArrayList<String>
        ArrayList<String> typeStringList = new ArrayList<>();
        // 获取数据库中题目数量不够的类型
        for (String key : typeCheck.keySet()) {
            Integer value = typeCheck.getInteger(key);
            // 数据库中实际存在数量
            int n1 = Integer.parseInt(typeCount.getOrDefault(key, 0).toString());
            // 用户传递过来想要数量
            int n2 = Integer.parseInt(value.toString());
            if (n1 < n2) {
                typeStringList.add(topicTypeDao.selectById(Integer.valueOf(key)));
            }
        }

        if (!typeStringList.isEmpty()) {
            String msg = String.join("、", typeStringList);
            tempResult.setFlag(false);
            tempResult.setMsg(msg + "的题库数量不足，请补充题库或修改随机数量！");
            return tempResult;
        }


        /**
         * 针对难度约束进行分配题目
         */
        // { "typeId": ArrayList<Topic>, }
        JSONObject typeTopicList = new JSONObject();
        // { "difficulty": [7,2,1] }
        JSONObject difficultyTypeCount = new JSONObject();
        for (String key : typeCheck.keySet()) {
            // 实际要的题量，例如：判断题->2
            int intValue = Integer.parseInt(typeCheck.getInteger(key).toString());
            typeTopicList.put(key, topicDao.selectByTypeId(uId, Integer.parseInt(key), tagId));
            double easy = difficultyCheck.getDouble("1");
            double middle = difficultyCheck.getDouble("2");
            double difficulty = difficultyCheck.getDouble("3");
            double difficultySum = easy + middle + difficulty;


            difficultyTypeCount.put(key,
                    new int[]{
                            // easy / difficultySum * intValue = 分配的题量
                            (int) (Math.ceil(easy / difficultySum * (double) intValue)),     // 简单
                            (int) (Math.ceil(middle / difficultySum * (double) intValue)),   // 中等
                            (int) (Math.ceil(difficulty / difficultySum * (double) intValue)),   // 困难
                    });

            // 实际随机到的题目数量：[1,1,0]
            int[] num = difficultyTypeCount.getObject(key, int[].class);
            // 题目总数
            int sum = Arrays.stream(num).reduce((left, right) -> left + right).orElse(0);
        }

        JSONObject papersResult = new JSONObject();
        // typeId题目类型
        for (String typeId : difficultyTypeCount.keySet()) {    // 先循环的题目类型
            papersResult.put(String.valueOf(typeId), new ArrayList<PapersContent>());

            // 题目类型的难度分布 1(typeId) -> [1,0,0] 代表判断题中的简单题为1题、中等题为0、困难题为0；
            int[] difficultyCount = difficultyTypeCount.getObject(typeId, int[].class);

            // 记录一下简单题的集合，防止在后续中等、困难的题不够的话，用简单代替
            ArrayList<Topic> easyPapers = new ArrayList<>();
            // i代表难度1-3
            for (int i = 0; i < difficultyCount.length; i++) {  // 题目的难易程度
                ArrayList<Topic> tempPapers = new ArrayList<>();
                // 已经筛选出typeId和difficultyId都满足的结果，在foreach中添加到新的列表中即可
                int finalI = i;

                // 拿到数据库中是“判断题｜单选题｜多选题｜填空题｜主观题”且是“简单题｜中等题｜难度题”的题目
                typeTopicList.getJSONArray(typeId)
                        .stream()
                        .map(item -> {
                            // item类型转化成Topic
                            ObjectMapper mapper = new ObjectMapper();
                            return mapper.convertValue(item, Topic.class);
                        })
                        .filter(topic -> Objects.equals(topic.getDifficultyId(), finalI + 1))
                        .forEach(tempPapers::add);

                // 以上，tempPapers就是数据库中满足条件的题目

                // 以下，tempPapers就是实际要存到papers中的题目
                // 代表要i这个难度要几题
                int topicNum = difficultyCount[i];
                // 如果subNum大于0，说明要删除题目，如果小于0说明要用简单题来补充
                int subNum = tempPapers.size() - topicNum;

                // 来缓存删除的数量
                int subNumTemp = subNum;

                // 题库数量充足
                if (subNum >= 0) {
                    for (int j = 0; j < subNum; j++) {
                        int randomNum = (int) (Math.random() * tempPapers.size());
                        // 随机删除的题目，相当于是多余的
                        Topic removeTopic = tempPapers.remove(randomNum);
                        // easyPapers所有的简单题列表，tempPapers.remove(randomNum)
                        if (finalI == 0) easyPapers.add(removeTopic);
                        subNumTemp--;
                    }
                } else {
                    for (int k = 0; k < -subNum; k++) {
                        int randomNum = (int) (Math.random() * easyPapers.size());
                        if (easyPapers.size() > randomNum) {
                            // 题库数量不够，用简单题目代替
                            tempPapers.add(easyPapers.remove(randomNum));
                            subNumTemp++;
                        }
                    }
                }

                if (subNumTemp < 0) {
                    String topicTypeName = topicTypeDao.selectById(Integer.valueOf(typeId));
                    String difficultyName = difficultyDao.selectById(i + 1);
                    tempResult.setFlag(false);
                    tempResult.setMsg(String.format("%s中的%s题数量不足，请补充题库或修改随机数量！", topicTypeName, difficultyName));
                    return tempResult;
                }

                tempPapers.forEach(topic -> {
                            PapersContent papersContent = new PapersContent();
                            papersContent.settId(topic.gettId());
                            papersContent.setQuestion(topic.getQuestion());
                            papersContent.setAnswer(topic.getAnswer());
                            papersContent.setScore(topicScore.getInteger(String.valueOf(topic.getTypeId())));
                            papersResult.getJSONArray(String.valueOf(typeId)).add(papersContent);
                        }
                );
            }
        }

        Integer integer = papersDao.addPaper(new Papers(uId, papersName, papersResult.toString(), LocalDateTime.now()));
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "试卷生成成功！" : Code.ERROR_MSG);
        return tempResult;
    }
}
