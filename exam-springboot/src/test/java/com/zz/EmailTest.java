package com.zz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class EmailTest {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void check() {
        ValueOperations<Object, Object> redisFor = redisTemplate.opsForValue();
        String code = "123456";
        String rightCode = (String) redisFor.get("2410360091@qq.com");
        System.out.println(rightCode);
        System.out.println(code.equals(rightCode));
    }
}
