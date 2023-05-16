package com.zz.Service.impl;

import com.zz.Service.TopicService;
import com.zz.bean.Topic;
import com.zz.dao.TopicDao;
import com.zz.utils.Code;
import com.zz.utils.result.TempResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicDao topicDao;

    @Override
    public TempResult addTopic(Topic topic) {
        TempResult tempResult = new TempResult();
        topic.setCreateTime(LocalDateTime.now());
        Integer integer = topicDao.addTopic(topic);
        tempResult.setFlag(integer != 0);
        tempResult.setMsg(tempResult.isFlag() ? "题目添加成功！" : Code.ERROR_MSG);
        return tempResult;
    }

    @Override
    public ArrayList<Topic> selectByUId(Integer uId) {
        return topicDao.selectByUId(uId);
    }
}
