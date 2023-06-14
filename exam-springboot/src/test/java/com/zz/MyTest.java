package com.zz;

import com.zz.dao.CourseDao;
import com.zz.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        System.out.println(jwtTokenUtil.parserToken("eyJhbGciOiJIUzNzM2NTkxOvOf-i1fXmXcTv7DePSpFMVVaIcHhLB_J936fVc9g2VZJOFLZE4hYrWwRPHtrDi_qlU8JmNV1jZGtJFAu5iN4w"));
    }
}
