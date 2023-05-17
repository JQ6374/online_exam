package com.zz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zz.dao.TopicDao;
import com.zz.dao.TopicTypeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TopicTest {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private TopicTypeDao topicTypeDao;

    @Test
    void selectTest(){
        System.out.println(topicDao.selectByUId(9));
    }

    @Test
    void addTest(){
        String test = "['A']";
        JSONArray parse = (JSONArray) JSON.parse(test);
        System.out.println();
        System.out.println(parse);
    }

    @Test
    void topicToPapers(){
        System.out.println(topicDao.topicToPapers(new Integer[] {1,2,3}));
    }
}
