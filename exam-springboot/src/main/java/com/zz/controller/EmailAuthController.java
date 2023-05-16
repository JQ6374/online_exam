package com.zz.controller;

import com.zz.Service.MailService;
import com.zz.utils.result.ApiResult;
import com.zz.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author liyuzhen
 */
@RestController
@RequestMapping("/api/auth")
public class EmailAuthController {

    /**
     * 引入业务层依赖
     */
    @Autowired
    private MailService mailService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private EmailUtils emailUtils;

    @PostMapping("/send_email")
    public ApiResult send_email(@RequestParam String toEmail) {
        ApiResult result = new ApiResult();
        String code = emailUtils.generateValidateCodeString(6);
        System.out.println(code);
        boolean flag = mailService.sendMail(toEmail, code);
        if (flag) {
            // 将生成的验证码保存到Redis中并设置有效期五分钟
            redisTemplate.opsForValue().set(toEmail, code,
                    5, TimeUnit.MINUTES);
        }
        result.setCode(200);
        result.setMsg(flag ? "发送成功！" : "服务器繁忙请稍后!");
        return result;
    }
}