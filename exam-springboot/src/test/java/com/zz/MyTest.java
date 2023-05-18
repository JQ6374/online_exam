package com.zz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zz.utils.AnswerUtils;
import com.zz.utils.CourseUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@SpringBootTest
public class MyTest {

    @Test
    void test() {
        String s = AnswerUtils.formatAnswer(1, "['A', 'B']");
        JSONObject json = JSON.parseObject(s);
        System.out.println(s);
        System.out.println(json);
        System.out.println(json.getJSONArray("answer"));

    }
}
