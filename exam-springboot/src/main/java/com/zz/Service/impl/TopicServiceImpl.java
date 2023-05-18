package com.zz.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zz.Service.TopicService;
import com.zz.bean.Papers;
import com.zz.bean.Topic;
import com.zz.bean.PapersContent;
import com.zz.dao.PapersDao;
import com.zz.dao.TopicDao;
import com.zz.dao.TopicTypeDao;
import com.zz.utils.AnswerUtils;
import com.zz.utils.Code;
import com.zz.utils.result.TempResult;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PapersDao papersDao;

    @Autowired
    private TopicTypeDao topicTypeDao;


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

    @Override
    public ArrayList<Topic> selectByUId(Integer uId) {
        return topicDao.selectByUId(uId);
    }

    @Override
    public TempResult delTopic(Integer tId) {
        TempResult tempResult = new TempResult();
        Integer integer = topicDao.delTopic(tId);
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "删除成功！" : Code.ERROR_MSG);
        return tempResult;
    }

    @Override
    public TempResult updateTopic(Topic topic) {
        TempResult tempResult = new TempResult();
        Integer integer = topicDao.updateTopic(topic);
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "修改成功！" : Code.ERROR_MSG);
        return tempResult;
    }

    @Override
    public TempResult topicToPapers(Integer[] tIds, String papersName, JSONObject topicScore) {
        JSONObject result = new JSONObject();
        topicTypeDao.selectAll().forEach(item ->
                result.put(String.valueOf(item.getTypeId()), new ArrayList<>()));
        TempResult tempResult = new TempResult();

        for (Topic topic : topicDao.topicToPapers(tIds)) {
            topic.setAnswer(AnswerUtils.formatAnswer(topic.getTypeId(), topic.getAnswer()));
            result.getJSONArray(String.valueOf(topic.getTypeId()))
                    .add(new PapersContent(
                            topic.gettId(),
                            topic.getQuestion(),
                            topic.getAnswer(),
                            topicScore.getInteger(String.valueOf(topic.getTypeId()))));
        }
        Integer integer = papersDao.addPaper(new Papers(papersName, result.toString(), LocalDateTime.now()));
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "试卷生成成功！" : Code.ERROR_MSG);
        return tempResult;
    }

    @Override
    public TempResult topicToPapers(JSONObject params) {
        TempResult tempResult = new TempResult();
        Integer uId = params.getInteger("uId");
        Integer tagId = params.getInteger("tagId");
        String papersName = params.getString("papersName");
        JSONObject topicScore = params.getJSONObject("topicScore");
        JSONObject check = params.getJSONObject("check");
        JSONObject typeCheck = check.getJSONObject("typeCheck");
        JSONObject difficultyCheck = check.getJSONObject("difficultyCheck");
        ArrayList<JSONObject> typeCountList = topicDao.typeCount(tagId);
        JSONObject typeCount = new JSONObject();
        typeCountList.forEach((item) -> {
            typeCount.put(item.getString("typeId"), item.getInteger("count"));
        });

        // 题目数量不够的类型 ArrayList<String>
        ArrayList<String> typeStringList = new ArrayList<>();
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

        // { "typeId": ArrayList<Topic>, }
        JSONObject typeTopicList = new JSONObject();
        // { "typeId": [7,2,1] }
        JSONObject difficultyTypeCount = new JSONObject();
        for (String key : typeCheck.keySet()) {
            int intValue = Integer.parseInt(typeCheck.getInteger(key).toString());
            typeTopicList.put(key, topicDao.selectByTypeId(uId, Integer.parseInt(key), tagId));
            double easy = difficultyCheck.getDouble("1");
            double middle = difficultyCheck.getDouble("2");
            double difficulty = difficultyCheck.getDouble("3");
            double difficultySum = easy + middle + difficulty;
            difficultyTypeCount.put(key,
                    new int[]{
                            (int) Math.round(easy / difficultySum * (double) intValue),
                            (int) Math.round(middle / difficultySum * (double) intValue),
                            (int) Math.round(difficulty / difficultySum * (double) intValue),
                    });
            int[] num = difficultyTypeCount.getObject(key, int[].class);
            int sum = Arrays.stream(num).reduce((left, right) -> left + right).orElse(0);
            if (intValue < sum) {
                num[0] -= (sum - intValue);
            }
        }

        JSONObject papersResult = new JSONObject();
        // typeId题目类型
        for (String typeId : difficultyTypeCount.keySet()) {
            papersResult.put(String.valueOf(typeId), new ArrayList<PapersContent>());

            // 题目类型的难度分布 1(typeId) -> [1,0,0] 代表判断题中的简单题为1题、中等题为0、困难题为0；
            int[] difficultyCount = difficultyTypeCount.getObject(typeId, int[].class);

            // 记录一下简单题的集合，防止在后续中等、困难的题不够的话，用简单代替
            ArrayList<Topic> easyPapers = new ArrayList<>();
            // i代表难度1-3
            for (int i = 0; i < difficultyCount.length; i++) {
                ArrayList<Topic> tempPapers = new ArrayList<>();
                // 已经筛选出typeId和difficultyId都满足的结果，在foreach中添加到新的列表中即可
                int finalI = i;
                typeTopicList.getJSONArray(typeId)
                        .stream()
                        .map(item -> {
                            ObjectMapper mapper = new ObjectMapper();
                            return mapper.convertValue(item, Topic.class);
                        })
                        .filter(topic -> Objects.equals(topic.getDifficultyId(), finalI + 1))
                        .forEach(tempPapers::add);
                easyPapers.addAll(tempPapers);

                // 代表要i这个难度要几题
                int topicNum = difficultyCount[i];
                // 需要随机删除的题目数量
                int subNum = tempPapers.size() - topicNum;

                // 题库数量充足
                if (subNum >= 0) {
                    for (int j = 0; j < subNum; j++) {
                        int randomNum = (int) (Math.random() * tempPapers.size());
                        tempPapers.remove(randomNum);
                        if (finalI == 0) easyPapers.remove(randomNum);
                    }
                } else {
                    int randomNum = (int) (Math.random() * tempPapers.size());
                    // 题库数量不够，用简单题目代替
                    tempPapers.add(easyPapers.remove(randomNum));
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

        Integer integer = papersDao.addPaper(new Papers(papersName, papersResult.toString(), LocalDateTime.now()));
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "试卷生成成功！" : Code.ERROR_MSG);
        return tempResult;
    }
}
