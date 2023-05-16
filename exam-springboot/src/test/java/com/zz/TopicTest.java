package com.zz;

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
}
