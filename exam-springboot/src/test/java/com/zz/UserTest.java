package com.zz;

import com.zz.bean.User;
import com.zz.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Test
    void insertTest() {
        User user = new User();
        user.setUsername("zjq");
        user.setPassword("123");
        Integer integer = userDao.addUser(user);
        System.out.println(integer);
    }
}
