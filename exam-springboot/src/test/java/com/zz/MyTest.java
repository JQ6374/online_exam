package com.zz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zz.dao.CourseDao;
import com.zz.utils.AnswerUtils;
import com.zz.utils.CourseUtils;
import com.zz.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.xml.ws.soap.Addressing;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@SpringBootTest
public class MyTest {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void test() {
        String token = jwtTokenUtil.generateToken("1");
        System.out.println(token);
        System.out.println(jwtTokenUtil.getUsernameFromToken(token));
    }
}
