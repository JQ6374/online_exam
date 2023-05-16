package com.zz;

import com.zz.bean.User;
import com.zz.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;


@SpringBootTest
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void insertTest() {
        User user = new User();
        user.setUsername("zjq");
        user.setPassword("123");
        Integer integer = userDao.addUser(user);
        System.out.println(integer);
    }

    @Test
    void selectAll() {
        User user = new User();
        user.setEmail("2410360091111@qq.com");
        ArrayList<User> integer = userDao.selectByEmail(user);
        System.out.println(integer.isEmpty());
        System.out.println(integer);
    }

    @Test
    void passwordEncode() {
        String password = "123";
        String encode = encoder.encode(password);
        System.out.println(encode);
        System.out.println(encoder.matches("123", encode));
    }

    @Test
    void update() {
        User user = new User();
        user.setPassword("123");
        user.setEmail("123");
        Integer integer = userDao.updateUser(user);
        System.out.println(integer);
    }
}
