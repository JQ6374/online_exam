package com.zz.objective;

import com.zz.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ObjectiveJudgetest {
    @Autowired
    private ExamService examService;
    @Test
    void testApartTopicType(){
    }
}
