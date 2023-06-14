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
//        String token = jwtTokenUtil.generateToken("1");
//        System.out.println(token);
//        System.out.println(jwtTokenUtil.parserToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJ1SWRcIjoyLFwidXNlcm5hbWVcIjpcIlQtSlFcIn0iLCJleHAiOjE2ODY3NTk0OTEsImlhdCI6MTY4Njc0MTQ5MX0.XJRFTeqqB1mwXSQMFLgADdX5rh1T7JRHieSZ3d19yVo7AMV2CASwY4SHsVOm9xgaCOxcF8FYv8Q92GNiHTrkRQ"));
        double a = 0.0;
        a += 1.0;
        System.out.println(a);

    }
}
